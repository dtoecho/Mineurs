select
    o.*,

    os.name os_name,

    p.name p_name,
    count(p.id) p_count,

    pm.name pm_name,
    m.name m_name
from
    `order` o
        left join `order_item` oi on o.id = oi.order_id
        left join order_state os on os.id = oi.order_state_id
        left join product_item pi on pi.id = oi.product_item_id
        left join product p on p.id = pi.product_id
        left join member m on m.id = o.member_id
        left join payment_method pm on pm.id = o.payment_method_id
group by o.id;