-- board
ALTER TABLE `mineurs_db`.`board` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`board` (`name`) VALUES ('Notice');
INSERT INTO `mineurs_db`.`board` (`name`) VALUES ('Review');
INSERT INTO `mineurs_db`.`board` (`name`) VALUES ('Qna');

-- cancel_return_exchange_type
ALTER TABLE `mineurs_db`.`cancel_return_exchange_type` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`cancel_return_exchange_type` (`name`) VALUES ('취소');
INSERT INTO `mineurs_db`.`cancel_return_exchange_type` (`name`) VALUES ('환불');
INSERT INTO `mineurs_db`.`cancel_return_exchange_type` (`name`) VALUES ('교환');

-- category
ALTER TABLE `mineurs_db`.`category` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`category` (`name`) VALUES ('Outer');
INSERT INTO `mineurs_db`.`category` (`name`) VALUES ('Bottom');
INSERT INTO `mineurs_db`.`category` (`name`) VALUES ('Top');
INSERT INTO `mineurs_db`.`category` (`name`) VALUES ('Dress');

-- color
ALTER TABLE `mineurs_db`.`color` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`color` (`kor_name`, `eng_name`, `hex_code`) VALUES ('핑크', 'pink', '#ffc0cb');
INSERT INTO `mineurs_db`.`color` (`kor_name`, `eng_name`, `hex_code`) VALUES ('아이보리', 'ivory', '#fffff0');
INSERT INTO `mineurs_db`.`color` (`kor_name`, `eng_name`, `hex_code`) VALUES ('네이비', 'navy', '#000080');
INSERT INTO `mineurs_db`.`color` (`kor_name`, `eng_name`, `hex_code`) VALUES ('레몬시폰', 'lemon chiffon', '#fffacd');

-- coupon
ALTER TABLE `mineurs_db`.`coupon` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`coupon` (`name`, `description`, `discount_rate`, `valid_date_start`, `valid_date_end`) VALUES ('무통장 입금시 5% 할인쿠폰', ' 5.0% 할인 (최대 10000원)', '0.95', '2024-01-01', '2024-12-31');

-- member
ALTER TABLE `mineurs_db`.`member` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`member` (`membername`, `name`, `phone_number`, `password`, `email`, `payment_password`) VALUES ('adreea123', '이재용', '01046467979', '1234', 'fjiww@gmail.com', '1234');
INSERT INTO `mineurs_db`.`member` (`membername`, `name`, `phone_number`, `password`, `email`, `payment_password`) VALUES ('dfijej121', '아이유', '01044341616', '2345', 'bjmir@naver.com', '7372');


-- notice
ALTER TABLE `mineurs_db`.`notice` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`notice` (`title`, `content`) VALUES ('* 모델 사이즈 *', '모델 사이즈');

-- order_state
ALTER TABLE `mineurs_db`.`order_state` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`order_state` (`name`) VALUES ('결제완료');
INSERT INTO `mineurs_db`.`order_state` (`name`) VALUES ('상품준비중');
INSERT INTO `mineurs_db`.`order_state` (`name`) VALUES ('배송시작');
INSERT INTO `mineurs_db`.`order_state` (`name`) VALUES ('배송중');
INSERT INTO `mineurs_db`.`order_state` (`name`) VALUES ('배송완료');

-- product
ALTER TABLE `mineurs_db`.`product` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`product` (`name`, `main_img_path`, `hit`, `description`, `is_displayed`, `is_sold`, `code`, `is_delivery_today`, `discount_rate`, `price`, `category_id`) VALUES ('Flavo sk *2 color', 'a6efbcb6a7c930e0967ea7d8b45c231e.gif', '12', '도톰한 니트 짜임의 스커트 소개해드릴게요 !', '1', '1', 'bt-0001', '1', '0.8', '13500', '2');
INSERT INTO `mineurs_db`.`product` (`name`, `main_img_path`, `hit`, `description`, `is_displayed`, `is_sold`, `code`, `is_delivery_today`, `discount_rate`, `price`, `category_id`) VALUES ('Peony knit buistier *3 color', '202404/4433e344ea00a917bd66330b55e48ffd.gif', '14', '러블리한 무드로 연출하기 좋은 니트 뷔스티에 소개해드릴게요 !', '1', '1', 'tp-0001', '0', '0.75', '38500', '3');

-- qna_category
ALTER TABLE `mineurs_db`.`qna_category` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`qna_category` (`name`) VALUES ('교환 / 반품 문의');
INSERT INTO `mineurs_db`.`qna_category` (`name`) VALUES ('배송 문의');
INSERT INTO `mineurs_db`.`qna_category` (`name`) VALUES ('상품 문의');
INSERT INTO `mineurs_db`.`qna_category` (`name`) VALUES ('배송 전 변경사항 문의');

-- size
ALTER TABLE `mineurs_db`.`size` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`size` (`eng_name`) VALUES ('s');
INSERT INTO `mineurs_db`.`size` (`eng_name`) VALUES ('m');
INSERT INTO `mineurs_db`.`size` (`eng_name`) VALUES ('l');

-- sort_method
ALTER TABLE `mineurs_db`.`sort_method` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`sort_method` (`kor_name`, `eng_name`) VALUES ('가격', 'price');
INSERT INTO `mineurs_db`.`sort_method` (`kor_name`, `eng_name`) VALUES ('상품명', 'name');
INSERT INTO `mineurs_db`.`sort_method` (`kor_name`, `eng_name`) VALUES ('등록일', 'date');
INSERT INTO `mineurs_db`.`sort_method` (`kor_name`, `eng_name`) VALUES ('조회수', 'hit');

-- search_method
ALTER TABLE `mineurs_db`.`search_method` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`search_method` (`name`) VALUES ('제목');
INSERT INTO `mineurs_db`.`search_method` (`name`) VALUES ('내용');
INSERT INTO `mineurs_db`.`search_method` (`name`) VALUES ('작성자');
INSERT INTO `mineurs_db`.`search_method` (`name`) VALUES ('상품명');
INSERT INTO `mineurs_db`.`search_method` (`name`) VALUES ('상품코드');

-- product_item
ALTER TABLE `mineurs_db`.`product_item` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`product_item` (`qty`, `code`, `product_id`, `size_id`, `color_id`) VALUES ('13', 'bt-0001-s-r', '1', '1', '1');
INSERT INTO `mineurs_db`.`product_item` (`qty`, `code`, `product_id`, `size_id`, `color_id`) VALUES ('11', 'tp-0001-s-r', '2', '1', '1');
INSERT INTO `mineurs_db`.`product_item` (`qty`, `code`, `product_id`, `size_id`, `color_id`) VALUES ('6', 'bt-0001-s-iv', '1', '1', '2');
INSERT INTO `mineurs_db`.`product_item` (`qty`, `code`, `product_id`, `size_id`, `color_id`) VALUES ('7', 'bt-0001-m-iv', '1', '2', '2');

-- cart
ALTER TABLE `mineurs_db`.`cart` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`cart` (`member_id`, `product_item_id`, `qty`) VALUES ('1', '1', '2');
INSERT INTO `mineurs_db`.`cart` (`member_id`, `product_item_id`, `qty`) VALUES ('1', '2', '1');
INSERT INTO `mineurs_db`.`cart` (`member_id`, `product_item_id`, `qty`) VALUES ('2', '1', '1');
INSERT INTO `mineurs_db`.`cart` (`member_id`, `product_item_id`, `qty`) VALUES ('2', '4', '2');

-- delivery_request_template
ALTER TABLE `mineurs_db`.`delivery_request_template` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`delivery_request_template` (`content`) VALUES ('문 앞');
INSERT INTO `mineurs_db`.`delivery_request_template` (`content`) VALUES ('직접 받고 부재시 문 앞');
INSERT INTO `mineurs_db`.`delivery_request_template` (`content`) VALUES ('경비실');
INSERT INTO `mineurs_db`.`delivery_request_template` (`content`) VALUES ('택배함');
INSERT INTO `mineurs_db`.`delivery_request_template` (`content`) VALUES ('그 외 장소 (예: 계단 밑, 옥상 등)');

-- cancel_return_exchange_reason
ALTER TABLE `mineurs_db`.`cancel_return_exchange_reason` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`cancel_return_exchange_reason` (`content`) VALUES ('상품이 마음에 들지 않음 (단순변심)');
INSERT INTO `mineurs_db`.`cancel_return_exchange_reason` (`content`) VALUES ('다른 상품 추가 후 재주문 예정');
INSERT INTO `mineurs_db`.`cancel_return_exchange_reason` (`content`) VALUES ('배송 상태가 멈취 있음');
INSERT INTO `mineurs_db`.`cancel_return_exchange_reason` (`content`) VALUES ('배송 지연이 예상됨');

-- cancel_return_exchange_state
ALTER TABLE `mineurs_db`.`cancel_return_exchange_state` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`cancel_return_exchange_state` (`name`) VALUES ('(취소) 환불 처리 대기');
INSERT INTO `mineurs_db`.`cancel_return_exchange_state` (`name`) VALUES ('완료');

-- delivery_info
ALTER TABLE `mineurs_db`.`delivery_info` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`delivery_info` (`receiver_name`, `phone_number`, `si_do`, `si_gun_gu`, `gu`, `road_address`, `is_default`, `member_id`, `delivery_request_template_id`) VALUES ('이재용', '01048482828', '경기도', '수원시', '영통구', '삼성로 129', '1', '1', '1');

-- like
ALTER TABLE `mineurs_db`.`like` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`like` (`member_id`, `product_id`) VALUES ('1', '1');
INSERT INTO `mineurs_db`.`like` (`member_id`, `product_id`) VALUES ('1', '2');
INSERT INTO `mineurs_db`.`like` (`member_id`, `product_id`) VALUES ('2', '1');

-- payment_method
ALTER TABLE `mineurs_db`.`payment_method` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`payment_method` (`name`, `card_number`, `member_id`) VALUES ('국민은행', '2030192939394040', '1');

-- order
ALTER TABLE `mineurs_db`.`order` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`order` (`code`, `total_product_price`, `total_delivery_fee`, `total_discount_amount`, `member_id`, `delivery_info_id`, `coupon_id`, `payment_method_id`) VALUES ('49192933', '69800', '6000', '4000', '1', '1', '1', '1');
INSERT INTO `mineurs_db`.`order` (`code`, `total_product_price`, `total_delivery_fee`, `total_discount_amount`, `member_id`, `delivery_info_id`, `coupon_id`, `payment_method_id`) VALUES ('jisoo123', '15570', '2000', '2000', '4', '2', '2', '2');
-- order_item
ALTER TABLE `mineurs_db`.`order_item` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`order_item` (`qty`, `total_price`, `code`, `delivery_fee`, `estimated_delivery_days`, `order_id`, `order_state_id`, `product_item_id`) VALUES ('2', '39900', '723857', '3000', '2', '1', '1', '1');
INSERT INTO `mineurs_db`.`order_item` (`qty`, `total_price`, `code`, `delivery_fee`, `estimated_delivery_days`, `order_id`, `order_state_id`, `product_item_id`) VALUES ('1', '29000', '543455', '3000', '2', '1', '1', '2');

-- qna
ALTER TABLE `mineurs_db`.`qna` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`qna` (`qna_category_id`, `content`, `state`, `answer_content`, `answer_reg_datetime`, `order_item_id`) VALUES ('1', '문의 할게 없어요', '0', '답변입니다', now(), '1');

-- review
ALTER TABLE `mineurs_db`.`review` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`review` (`summary`, `content`, `rating`, `order_item_id`) VALUES ('체험단에서 돈받고 작성한 후기입니다', '이제품 너무너무 좋네요', '5', '1');

-- review_img
ALTER TABLE `mineurs_db`.`review_img` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`review_img` (`path`, `review_id`) VALUES ('이미지1.jpg', '1');
INSERT INTO `mineurs_db`.`review_img` (`path`, `review_id`) VALUES ('이미지2.jpg', '1');

-- review_like
ALTER TABLE `mineurs_db`.`review_like` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`review_like` (`member_id`, `review_id`) VALUES ('1', '1');
INSERT INTO `mineurs_db`.`review_like` (`member_id`, `review_id`) VALUES ('2', '1');

-- search_method
ALTER TABLE `mineurs_db`.`sub_img` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`sub_img` (`path`, `product_id`) VALUES ('이미지1.jpg', '1');
INSERT INTO `mineurs_db`.`sub_img` (`path`, `product_id`) VALUES ('이미지2.jpg', '1');
INSERT INTO `mineurs_db`.`sub_img` (`path`, `product_id`) VALUES ('이미지1.jpg', '2');

-- member_coupon
ALTER TABLE `mineurs_db`.`member_coupon` AUTO_INCREMENT = 1;
INSERT INTO `mineurs_db`.`member_coupon` (`member_id`, `coupon_id`, `used`) VALUES ('1', '1', '0');
INSERT INTO `mineurs_db`.`member_coupon` (`member_id`, `coupon_id`, `used`) VALUES ('2', '1', '0');



