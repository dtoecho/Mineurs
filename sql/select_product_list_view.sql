select p.*,

       ct.id         ct_id,
       ct.name       ct_name,

       pi.id         pi_id,
       pi.qty        pi_qty,
       pi.`code`     pi_code,
       pi.product_id pi_product_id,
       pi.size_id    pi_size_id,
       pi.color_id   pi_color_id,

       s.id          s_id,
       s.eng_name    s_eng_name,

       c.id          c_id,
       c.kor_name    c_kor_name,
       c.eng_name    c_eng_name,
       c.hex_code    c_hex_code

from (select p.*, count(l.member_id) like_count
      from product p
               left join `like` l on l.product_id = p.id
      group by p.id) p
         left join category ct on ct.id = p.category_id
         left join product_item pi on pi.product_id = p.id
         left join size s on s.id = pi.size_id
         left join color c on c.id = pi.color_id;