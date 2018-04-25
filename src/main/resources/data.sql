INSERT INTO user VALUES (1 , 'ebatsukh@mum.edu', '$2a$10$snL9/9oR.u6CtqN/g5hJm.GtmHr4wx1ptuOD8.GL8wR4yvHg8Y/7O', 'ENABLED',
                                  'erdenebayar');
INSERT INTO admin VALUES ('Erdenebayar', 'Batsukh', 1);


INSERT INTO user VALUES (2 , 'ebatsukh@mum.edu', '$2a$10$snL9/9oR.u6CtqN/g5hJm.GtmHr4wx1ptuOD8.GL8wR4yvHg8Y/7O', 'ENABLED',
                         'vendor');
INSERT INTO vendor VALUES ('MUM', 2);

INSERT INTO user VALUES (3, 'ylai@mum.edu','$2a$10$ibMbCkRcLFo4snX8071GSuJ9bV15Jvl9wFNafEkiSBTFuFt8m9BtO', 'ENABLED', 'yeerick');
INSERT INTO customer VALUES ('Yee Rick', 'Lai', 3);
INSERT INTO card_detail VALUES(1, '11/20', 'Yee Rick Lai', '$2a$10$b1eHDXU3TQnCrLBSr1o25OW9o0mo./esjdwkK8JZTyTYl0ggKNutK', 'Visa', '$2a$10$ssDys2u9Qvas6pi0JgATRem3RvVKtJy4r1YVP7G8R.VRwaX6lZoIK', '1111', 'ENABLED', '52557', 3);
INSERT INTO address VALUES (1, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '1000 N 4th St', '52557', 3);

INSERT INTO product VALUES(1, 'Branded Mechanical Watch', 'ROLEX VISION',  20000, 3, 'ENABLED', NULL, 2);
INSERT INTO order_detail VALUES(1, 20000, 3, 1, 1);
INSERT INTO `order` VALUES (1, DATE '2018-4-4', DATE '2018-4-4', 'ENABLED', 1, 1, 3);