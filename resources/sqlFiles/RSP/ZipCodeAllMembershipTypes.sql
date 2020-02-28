select x.mbr_id_16, x.mbr_fst_nm, x.mbr_lst_nm, y.zip
from tokyo_inq.memberx x 
inner join tokyo_inq.member_addressx y on x.clb_cd = y.clb_cd and x.mbr_id = y.mbr_id
where x.mbrs_sts_cd = 'A' and y.zip in ('32746','32750','32738') order by dbms_random.value 
--20748 records based on zip codes
--general query, all membership types with in specified zip codes