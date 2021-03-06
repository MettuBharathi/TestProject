select COUNT (MEMBERX.MBR_ID_16), MBR_ID_16

FROM TOKYO_ERS_HIST.ARCH_CALL

INNER JOIN TOKYO_INQ.MEMBERSHIPX ON ARCH_CALL.SC_CALL_MBR_ID=MEMBERSHIPX.MBR_ID

INNER JOIN TOKYO_INQ.MEMBERX
ON (ARCH_CALL.SC_CALL_MBR_ID=MEMBERX.MBR_ID
AND ARCH_CALL.SC_CALL_ASC_ID=MEMBERX.ASSOC_MBR_ID)
WHERE SC_DT                 > '01-JAN-17'
AND CHG_ENTITLEMENT         = 'Y'
AND ARCH_CALL.PLUS_IND                = 'E'
AND MEMBERX.MBRS_STS_CD             = 'A'
AND TOKYO_INQ.MEMBERSHIPX.MBRS_EXPIR_DT > '01-JAN-18'
AND rownum                  < 50
group by MBR_ID_16 having count (MEMBERX.MBR_ID_16) > 4
ORDER BY dbms_random.value