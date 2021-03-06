SELECT MEMBERSHIPX.CLB_CD,
   MEMBERSHIPX.MBR_ID,
   MEMBERX.ASSOC_MBR_ID,
   MEMBERSHIPX.MBRS_EXPIR_DT,
   MEMBERSHIPX.MBRS_PLS_IN,
   MEMBERX.MBRS_STS_CD,
      MEMBERX.MBR_LST_NM,
      MEMBERX.MBR_FST_NM,
   MEMBERX.MBR_ID_16
   
 FROM TOKYO_INQ.MEMBERSHIPX
 
 INNER JOIN TOKYO_INQ.MEMBERX 
 ON MEMBERSHIPX.MBR_ID=MEMBERX.MBR_ID WHERE MBRS_EXPIR_DT > '01-JAN-18' and MBRS_PLS_IN = 'N' AND MEMBERX.MBRS_STS_CD = 'P' AND rownum < 10000
   ORDER BY dbms_random.value