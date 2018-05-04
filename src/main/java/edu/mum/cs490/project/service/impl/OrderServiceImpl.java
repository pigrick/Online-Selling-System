package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.framework.observer.*;
import edu.mum.cs490.project.framework.template.PurchaseTemplate;
import edu.mum.cs490.project.framework.template.impl.PurchaseTemplateImpl;
import edu.mum.cs490.project.repository.AddressRepository;
import edu.mum.cs490.project.repository.CardDetailRepository;
import edu.mum.cs490.project.repository.OrderRepository;
import edu.mum.cs490.project.service.OrderService;
import edu.mum.cs490.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRespository;
    private final CardDetailRepository cardDetailRepository;
    private final PaymentService paymentService;
    private final AddressRepository addressRepository;

    private static final int PAGE_SIZE = 5;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CardDetailRepository cardDetailRepository,
                            PaymentService paymentService, AddressRepository addressRepository){
        this.orderRespository = orderRepository;
        this.cardDetailRepository = cardDetailRepository;
        this.paymentService = paymentService;
        this.addressRepository = addressRepository;
    }

    @Value("${card.detail.id.oss}")
    private Integer cardDetailIdOSS;

    @Value("${card.detail.id.tax}")
    private Integer cardDetailIdTAX;

    @Override
    public List<Order> findAll() {
        return this.orderRespository.findAll();
    }

    @Override
    public List<Order> findallEnabledByCustomer_id(Integer customerId) {
        return this.orderRespository.findByStatusAndCustomer_id(Status.ENABLED, customerId);
    }



    //Order Manipulation
    @Override
    public List<Order> findByCustomer_id(Integer customerId) {
        return this.orderRespository.findByCustomer_id(customerId);
    }

    @Override
    public List<Order> findByVendor_id(Integer vendorId) {
        return this.orderRespository.findByVendor_id(vendorId);
    }

    @Override
    public Order findById(Integer orderId) {
        return this.orderRespository.findById(orderId).orElse(null);
    }

    @Override
    public Page<Order> findByCustomer_id(Integer customerId, int page) {
        return this.orderRespository.findByCustomer_idOrderByOrderDateDesc(customerId, PageRequest.of(page-1, PAGE_SIZE));
    }

    @Override
    public List<Order> findByVendor_idBetweenDate(Integer vendorId, Date begin, Date end) {
        return this.orderRespository.findByVendor_idBetweenDate(vendorId, begin, end);
    }

    @Override
    public List<CardDetail> findCardByUser_id(Integer userId) {
        return this.cardDetailRepository.findByOwner_idAndStatus(userId, Status.ENABLED);
    }

    @Override
    public CardDetail findCardById(Integer cardId) {
        return this.cardDetailRepository.findById(cardId).orElse(null);
    }

    @Override
    public void disableCard(Integer cardId) {
        this.cardDetailRepository.disableCard(cardId);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        this.cardDetailRepository.save(order.getCard());
        this.addressRepository.save(order.getAddress());
        return this.orderRespository.save(order);
    }

    @Override
    public Integer purchase(Order order) {
        CardDetail OSSCardDetail = cardDetailRepository.findById(cardDetailIdOSS).get();
        CardDetail TAXCardDetail = cardDetailRepository.findById(cardDetailIdTAX).get();
        PurchaseTemplate purchaseTemplate = getPurchaseTemplate(order, OSSCardDetail, TAXCardDetail);
        return purchaseTemplate.process();
    }


    private PurchaseTemplate getPurchaseTemplate(Order order, CardDetail OSSCardDetail, CardDetail taxCardDetail) {
        NotifierSubject notifierSubject = new NotifierSubject();
        notifierSubject.addObserver(new MailToCustomerObserver());
        notifierSubject.addObserver(new MailToVendorObserver());

        TransferSubject transferSubject = new TransferSubject();
        transferSubject.addObserver(new TransferToVendorObserver(order, OSSCardDetail, paymentService, cardDetailRepository));
        transferSubject.addObserver(new TransferToTAXObserver(order, OSSCardDetail, taxCardDetail, paymentService));
        PurchaseTemplate purchaseTemplate = new PurchaseTemplateImpl(order, OSSCardDetail, notifierSubject, transferSubject, paymentService);
        return purchaseTemplate;
    }
}
