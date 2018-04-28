INSERT INTO user VALUES (1 , 'ebatsukh@mum.edu', '$2a$10$snL9/9oR.u6CtqN/g5hJm.GtmHr4wx1ptuOD8.GL8wR4yvHg8Y/7O', 'ENABLED',
                                  'erdenebayar');
INSERT INTO user VALUES (4 , 'akron@mum.edu', '$2a$10$5CROa6Yafyrk/NdPLtUIwemYIjWlh9fSMtGEIuKfmWN98dXMyvUYi', 'ENABLED','akron');
INSERT INTO user VALUES (5 , 'bartiet@mum.edu', '$2a$10$5CROa6Yafyrk/NdPLtUIwemYIjWlh9fSMtGEIuKfmWN98dXMyvUYi', 'ENABLED','bartiet');
INSERT INTO user VALUES (6 , 'gamet@mum.edu', '$2a$10$5CROa6Yafyrk/NdPLtUIwemYIjWlh9fSMtGEIuKfmWN98dXMyvUYi', 'ENABLED','gamet');
INSERT INTO user VALUES (7 , 'panasonic@mum.edu', '$2a$10$5CROa6Yafyrk/NdPLtUIwemYIjWlh9fSMtGEIuKfmWN98dXMyvUYi', 'ENABLED','panasonic');


INSERT INTO admin VALUES ('Erdenebayar', 'Batsukh', 1);


INSERT INTO user VALUES (2 , 'ebatsukh@mum.edu', '$2a$10$snL9/9oR.u6CtqN/g5hJm.GtmHr4wx1ptuOD8.GL8wR4yvHg8Y/7O', 'ENABLED',
                         'vendor');
INSERT INTO vendor VALUES ('MUM', 2);
INSERT INTO vendor VALUES ('AKRON', 4);
INSERT INTO vendor VALUES ('BARTIET', 5);
INSERT INTO vendor VALUES ('GAMET', 6);
INSERT INTO vendor VALUES ('PANASONIC', 7);

INSERT INTO category VALUES (1, "Clothing",1);
INSERT INTO category VALUES (2, "Watches",1);
INSERT INTO category VALUES (3, "Shoes",1);
INSERT INTO category VALUES (4, "Bag",1);


INSERT INTO user VALUES (3, 'ylai@mum.edu','$2a$10$ibMbCkRcLFo4snX8071GSuJ9bV15Jvl9wFNafEkiSBTFuFt8m9BtO', 'ENABLED', 'yeerick');
INSERT INTO customer VALUES ('Yee Rick', 'Lai', 3);
INSERT INTO card_detail VALUES(1, '11/20', 'Yee Rick Lai', '$2a$10$b1eHDXU3TQnCrLBSr1o25OW9o0mo./esjdwkK8JZTyTYl0ggKNutK', 'Visa', '$2a$10$ssDys2u9Qvas6pi0JgATRem3RvVKtJy4r1YVP7G8R.VRwaX6lZoIK', '1111', 'ENABLED', '52557', 3);
INSERT INTO address VALUES (1, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '1000 N 4th St', '52557', 3);

INSERT INTO product VALUES(1, 'Branded Mechanical Watch', 'ROLEX VISION',  20000, 3, 'ENABLED', 2, 2);
INSERT INTO product VALUES(2, 'Jean', 'Livi',  200, 3, 'ENABLED', 1, 2);
INSERT INTO product VALUES(3, 'shoes', 'Rothy Grey ',  125, 10, 'ENABLED', 3, 4);
INSERT INTO product VALUES(4, 'Women Leather Market Tote Bag by Everlane in Blush', 'Everlane ',  165, 10, 'ENABLED', 4, 5);

INSERT INTO order_detail VALUES(1, 20000, 3, 1, 1);
INSERT INTO `order` VALUES (1, DATE '2018-4-4', DATE '2018-4-4', 'ENABLED', 1, 1, 3);
