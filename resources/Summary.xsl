<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">
		<html>

			<head>
			
				<style type="text/css">
				table{
				<!-- border: 1px solid black;-->
				border-collapse: collapse;
				}
					table.tfmt {
					 border-collapse: collapse;
					}
					td.header{
					
					}

					td.colfmt {
					 
					 border-collapse: collapse;	
					
					 background-color: red;
					 color: black;
					 text-align: center;
					}
					td.results{
					border-color-blue;
					text-align: center;
					 border: 1px solid black;
					border-collapse: collapse;

					font-family:'AmstelvarAlpha';
					font-style:normal;
					}
					
					th {
					background-color: #2F97C1; color: white; border-color:black; border: 1px solid black; border-collapse: collapse;
					}
					
					table.results{
					
					 border: 1px solid black;
					border-collapse: collapse;
					border-color-blue;
					border-color:black;
					}
					div {
					  background-color: white;
					  padding-top: 80px;
					  padding-right: 180px;
					  padding-bottom: 80px;
					  padding-left: 180px;
					}
					.topright {
						  position: absolute;
						  top: 20px;
						  right: 50px;
						  font-size: 18px;
						}
					.topleft {
						  position: absolute;
						  top: 20px;
						  left: 50px;
						  font-size: 18px;
						}

				</style>
			</head>

			<body>

			
			
			
			<div>
			<table >
			
			<tr>
						<img class="topleft" SRC="D:\insight.JPG" ALT="some text" WIDTH="80" HEIGHT="60">
						</img>

						<img class= "topright" SRC="D:\data\fullFrameworks\BBAAviation\Java-Selenium\logos\cigniti_logo.png" ALT="some text" WIDTH="80" HEIGHT="60" >
						</img>
			</tr>
				
			</table>
<table >
			
			<tr>
			<th style="width:1050px;background-color:red" >Test Summary Report 
			<b><xsl:value-of select="TestCaseName"/></b>
			</th>
			</tr>
			
			</table>
			<table >
		
			<xsl:for-each select="Summary/Header">
						<tr>
							<th style="width:50px">
							
							Browser: 
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="Browser"/></i><br></br>
							
							</th>
							<th style="width:50px;background-color: white;border:none; float: center" rowspan='9 '><canvas id="myChart" width="400" height="200"  ></canvas> 
					</th>
						
						</tr>
						<tr>
							<th style="width:50px">
							
							SuiteExecuted: 
							</th>
							<th style="width:50px;word-wrap: break-word">
							<i> <xsl:value-of select="SuiteExecuted"/></i><br></br>
							
							</th>
						</tr>	

						<!--<tr>
							<th style="width:50px">
							
							Status: 
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="Status"/></i><br></br>
							
							</th>
						</tr>-->
						<tr>
							<th style="width:50px">
							
							OS:  
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="OS"/></i><br></br>
							
							</th>
						</tr>
						<tr>
							<th style="width:50px">
							
							HostName:   
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="HostName"/></i><br></br>
							
							</th>
						</tr>
						<tr>
							<th style="width:50px">
							
							Version:   
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="Version"/></i><br></br>
							
							</th>
						</tr>
						<tr>
							<th style="width:50px">
							
							DateTime:   
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="DateTime"/></i><br></br>
							
							</th>
						</tr>
						<tr>
							<th style="width:50px">
							
							Environment:   
							</th>
							<th style="width:50px">
							<i> <xsl:value-of select="Environment"/></i><br></br>
							
							</th>
							
						</tr>
					
			</xsl:for-each >
			
			</table>
									
						
<script src="D:\XML\Chart.bundle.js"> 
</script> 


<script type="text/javascript"> 
<xsl:for-each select="Summary/Header">
var ctx = document.getElementById("myChart"); 
var myChart = new Chart(ctx, { 
type: 'pie', 
data: { 
    labels: ["Pass", "Fail"], 
    datasets: [ 
    { label: '# of students', 
        data: [<xsl:value-of select="TPassCount" />,<xsl:value-of select="TFailCount" />], 
        backgroundColor :['#008000', 
                
                '#FF0000' 
], 

borderColor: [ 
                'rgba(42,199,61,1)', 
                
                'rgba(255, 0, 0, 1)' 
            ], 
borderWidth : 1 
    } 
    ] 
}, 

}); 
</xsl:for-each>

    </script> 
			
			
				<table class="tfmt">
					<tr>
						<th style="width:50px" >S.No</th>
						<th style="width:250px">TestCase</th>
						<th style="width:250px">Description</th>
						<th style="width:150px">PassCount</th>
						<th style="width:150px">FailCount</th>
						<th style="width:150px">Status</th>
						<th style="width:150px">Time</th>
					</tr>

					<xsl:for-each select="Summary/TestDetails">
						<tr>
							<td class="colfmt, results">
								<xsl:value-of select="SerialNo" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="TestCase" />
								<a >
									<xsl:attribute name="href"><xsl:value-of select="TestCase"/>.html</xsl:attribute>
									<!--<xsl:value-of select="Status" target = "Status" SRC="Status" img="/{@Status}"/>-->
									<!--<xsl:value-of select="Status" target = "Status" SRC="Status" img="$Status"/>-->
								<!--	<img WIDTH="30" HEIGHT="30"><xsl:attribute name="src" ><xsl:value-of select="TestCase" /></xsl:attribute>.html</img>
-->
								</a>
							</td>
							<td class="colfmt, results" >
								<xsl:value-of select="Description" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="PassCount" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="FailCount" />
							</td>
							
							<td class="colfmt, results">
							<xsl:value-of select="Status" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="Time" />
							</td>
						</tr>

					</xsl:for-each>
				</table>
				
			<!--	<table >
				<xsl:for-each select="TestCaseDetails/TestCase">

						<tr>
							<th style="width:1080px" >
								
									Time Taken to Execution:								
								<xsl:value-of select="TestCaseExecutionTimeInSeconds" />
							</th>
						</tr>
			</xsl:for-each>
			</table>
				
			<table>
			<xsl:for-each select="TestCaseDetails/TestCase">
				<tr>
						<th style="width:260px">PassCount:</th>
						<th style="width:260px">
						<xsl:value-of select="PassCount" />
						</th>
						<th style="width:260px">FailCount</th>
						<th style="width:260px"><xsl:value-of select="FailCount" /></th>
					</tr>	
					
			</xsl:for-each>
			</table> -->
				
				</div>
			</body>
			
		</html>
	</xsl:template>
</xsl:stylesheet>