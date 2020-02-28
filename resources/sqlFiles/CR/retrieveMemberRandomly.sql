SELECT MBR_ID_16
	FROM tokyo_inq.MEMBERX
	WHERE MBRS_STS_CD = 'A'
      AND clb_cd = '981'
	  AND rownum < 1000
	  ORDER BY dbms_random.value