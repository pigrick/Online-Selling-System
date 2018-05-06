# CREATE USER 'oss'@'localhost' IDENTIFIED BY 'oss';
# GRANT ALL PRIVILEGES ON OSS.* TO 'oss'@'localhost';
# FLUSH PRIVILEGES;

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

INSERT INTO category VALUES (1, "Women",'ENABLED',null);
INSERT INTO category VALUES (2, "Men",'ENABLED',null);
INSERT INTO category VALUES (3, "Clothing",'ENABLED',1);
INSERT INTO category VALUES (4, "Bag",'ENABLED',1);
INSERT INTO category VALUES (5, "Watches",'ENABLED',2);
INSERT INTO category VALUES (6, "Shoes",'ENABLED',2);
INSERT INTO category VALUES (7, "Accessories",'ENABLED',5);


INSERT INTO user VALUES (3, 'ylai@mum.edu','$2a$10$ibMbCkRcLFo4snX8071GSuJ9bV15Jvl9wFNafEkiSBTFuFt8m9BtO', 'ENABLED', 'yeerick');
INSERT INTO customer VALUES ('Yee Rick', 'Lai', 3);
--4000300020001000 100 05/2020 OSS 52557
--4000300020002000 200 05/2020 TAX 10000
--4000300020003001 301 05/2020 V1 52557
--4000300020003002 302 05/2020 V2 52557
INSERT INTO card_detail VALUES(1, 'QraOsmY1Vm/D/pQN6BuLhw==', 'hiE+wqyvnTqJkKbp/OsjdA==', 'wCs2kVuyP/Wl1r1z25CSLvgzFwyz9ICwGILHUe1Ku8c=', 'vSi7w/I1S+ABSrUo+vlbIQ==', 'PDxRRthWgxfXXzrhUr4D1w==', '1000', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 1);
INSERT INTO card_detail VALUES(2, 'QraOsmY1Vm/D/pQN6BuLhw==', 'SCyf4mtAPdUbXAUeuJXTZg==', 'ZsI1f6td8VkuQmP2o7zrsvgzFwyz9ICwGILHUe1Ku8c=', 'vSi7w/I1S+ABSrUo+vlbIQ==', 'E+xQ7+d1sHpp+x5e9vK7UA==', '2000', 'ENABLED', 'VV8Po+pw3CYRaB151M9MdQ==', NULL, NULL);
INSERT INTO card_detail VALUES(3, 'QraOsmY1Vm/D/pQN6BuLhw==', 'U20GoH1QCTVt89PhV9iiBQ==', 'CyIxvjmUDvX8d3MAODdv+PgzFwyz9ICwGILHUe1Ku8c=', 'vSi7w/I1S+ABSrUo+vlbIQ==', 'iU6EhOP10yd+6m1+7hxHgg==', '3001', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 2);
INSERT INTO card_detail VALUES(4, 'QraOsmY1Vm/D/pQN6BuLhw==', 'ZJENHTyNg0q7K2Nj4j5E0g==', 'xljjdxpBdvEfFMGJtIhCx/gzFwyz9ICwGILHUe1Ku8c=', 'vSi7w/I1S+ABSrUo+vlbIQ==', 'qut4mUwppXC4xP3Sbj782w==', '3002', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 4);
INSERT INTO card_detail VALUES(5, 'WiHDur8q3aeQmyi87fjwaw==', '5wM9IyibGkIBralK1Lb6vA==', 'gMJ7svtpR5YGu5UiYXg0G/gzFwyz9ICwGILHUe1Ku8c=', 'Visa', 'AIZ9VtyuXwhp/Wj+Cz4yug==', '3699', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 3);

INSERT INTO card_detail VALUES(6, 'QraOsmY1Vm/D/pQN6BuLhw==', 'hiE+wqyvnTqJkKbp/OsjdA==', 'wCs2kVuyP/Wl1r1z25CSLvgzFwyz9ICwGILHUe1Ku8c=', 'Visa', 'PDxRRthWgxfXXzrhUr4D1w==', '1000', 'ENABLED', 'PM0IK0uDDL5hne6fEhSiQg==', NULL, 3);
INSERT INTO address VALUES (1, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '1000 N 4th St', '52557', 3);
INSERT INTO address VALUES (2, 'Fairfield', '2058871599', 'Iowa', 'ENABLED', '52 E. Golden Lane', '52556', 3);

INSERT INTO product VALUES(1, 'Branded Mechanical Watch', 'product\\1\\1.png', 'ROLEX VISION',  150, 3, 'ENABLED', 1, 2 );
INSERT INTO product VALUES(2, 'Jean', 'product\\2\\2.png', 'Livi',  200, 3, 'ENABLED', 2, 2);
INSERT INTO product VALUES(3, 'shoes', 'product\\3\\3.png' , 'Rothy Grey ',  125, 10, 'ENABLED', 3, 4);
INSERT INTO product VALUES(4, 'Women Leather Market Tote Bag by Everlane in Blush', 'product\\4\\4.png', 'Everlane ',  165, 10, 'ENABLED', 4, 5 );

INSERT INTO order_detail VALUES(1, 20000, 3, 1, 1);
INSERT INTO `order` VALUES (1, DATE '2018-4-4', DATE '2018-4-4', 'ENABLED', 1, 1, 3, NULL);
