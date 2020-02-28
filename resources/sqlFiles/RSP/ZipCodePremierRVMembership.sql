select x.clb_cd, x.mbr_id_16, y.mbr_ride_cd, x.mbrs_sts_cd, z.zip
from tokyo_inq.memberx x
inner join tokyo_inq.riderx y on x.mbr_id = y.mbr_id and x.assoc_mbr_id = y.assoc_mbr_id
inner join tokyo_inq.member_addressx z on y.mbr_id = z.mbr_id
where y.mbr_ride_cd = 'V' and x.mbrs_sts_cd = 'A' and z.zip in ('32746','32750','32738')
--Premier RV, 86 records
--This query returns PremierRV memberships within the listed zip codes for Promotions