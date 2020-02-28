SELECT MEMBERSHIPX.CLB_CD,
   MEMBERSHIPX.MBR_ID,
   MEMBERX.ASSOC_MBR_ID,
   MEMBERSHIPX.MBRS_EXPIR_DT,
   MEMBERSHIPX.MBRS_PLS_IN,
   MEMBERSHIPX.MBRS_STS_CD,
   MEMBERX.MBR_ID_16,
   MEMBERX.MBR_LST_NM,
      MEMBERX.MBR_FST_NM,
   RIDERX.MBR_RIDE_CD
 
 FROM TOKYO_INQ.MEMBERSHIPX
 
 INNER JOIN TOKYO_INQ.MEMBERX 
 ON MEMBERSHIPX.MBR_ID=MEMBERX.MBR_ID
 
 INNER JOIN TOKYO_INQ.RIDERX 
 ON MEMBERSHIPX.MBR_ID=RIDERX.MBR_ID WHERE MBRS_EXPIR_DT > '01-JAN-18' and MBRS_PLS_IN = 'Y' and MBR_RIDE_CD = 'RV' AND MEMBERX.MBRS_STS_CD = 'A' AND rownum < 10000
   ORDER BY dbms_random.value
