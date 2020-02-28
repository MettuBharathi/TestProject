<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">
		<html>

			<head>
			
				<style type="text/css">
				table{
				 border: 1px solid black;
				border-collapse: collapse;
				}
					table.tfmt {
					 border-collapse: collapse;
					}
					td.header{
					
					}

					td.colfmt {
					 border: 1px solid black;
					 border-collapse: collapse;	
					border-color:black;
					background-color: red;
					color: black;
					text-align: center;
					}
					td.results{
					border: 1px solid black
					border-color-blue;
					text-align: center;
					 border: 1px solid black;
					border-collapse: collapse;

					font-family:'AmstelvarAlpha';
					font-style:normal;
					}
					

					th {
					background-color: #2F97C1;
					color: white;
					border-color:black;
					 border: 1px solid black;
					border-collapse: collapse;
					}
					table.results{
					
					 border: 1px solid black;
					border-collapse: collapse;
					border-color-blue;
					border-color:black;
					}
					div {
					  border: 1px solid black;
					  background-color: white;
					  padding-top: 80px;
					  padding-right: 180px;
					  padding-bottom: 80px;
					  padding-left: 180px;
					  border-color:black;
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
			<xsl:for-each select="TestCaseDetails/TestCase">
			<tr>
			<th style="width:1080px" >TestCase Name: 
			<b><xsl:value-of select="TestCaseName"/></b>
			</th>
			</tr>
			</xsl:for-each>
			</table>
			<table>
		
			<xsl:for-each select="TestCaseDetails/TestCase">
			<!--<tr>
						<th style="width:260px">Browser:</th>
						<th style="width:260px"><xsl:value-of select="Browser" /></th>
						<th style="width:260px">Execution Time</th>
						<th style="width:260px"><xsl:value-of select="StartTime" /></th>
						<th style="width:260px">TestCaseDescription</th>
						<th style="width:260px"><xsl:value-of select="TestCaseDescription" /></th>
						
					</tr>	
					
					<tr>	
						<th style="width:260px">Status</th>
						<th style="width:260px"><xsl:value-of select="Status" /></th>
						<th style="width:260px">OS</th>
						<th style="width:260px"><xsl:value-of select="OS" /></th>
						<th style="width:260px">HostName</th>
						<th style="width:260px"><xsl:value-of select="HostName" /></th>
					</tr> -->
					<tr style="border:1px solid black">
					<th style="width:825px; height:100px;border: 1px solid black">
							Browser: <i> <xsl:value-of select="Browser"/></i><br></br>
							_______________________________________________________________________________________<br>
							<br></br>Execution Date: <i><xsl:value-of select="ExecutionDate" /></i><br>
							_______________________________________________________________________________________</br>
							<br></br>Status: <i><xsl:value-of select="Status" /></i><br>
							_______________________________________________________________________________________</br>
							<br></br>OS: <i><xsl:value-of select="OS" /></i><br>
							_______________________________________________________________________________________</br>
							<br></br>HostName: <i><xsl:value-of select="HostName" /></i>
							_______________________________________________________________________________________</br>
					</th>
				<!--	<th style="width:260px">Execution Date: <br><br><i><xsl:value-of select="ExecutionDate" /></i></br></br></th>
					<th style="width:260px">Status: <br><br><i><xsl:value-of select="Status" /></i></br></br></th>
					<th style="width:260px">OS: <br><br><i><xsl:value-of select="OS" /></i></br></br></th>
					<th style="width:260px">HostName: <br><br><i><xsl:value-of select="HostName" /></i></br></br></th>-->
					<th style="width:175px;background-color: white"><canvas id="myChart" width="40" height="40"  ></canvas> 
					</th>
					</tr>
			</xsl:for-each >
			</table>
									
						
<script src="D:\XML\Chart.bundle.js"> 
</script> 


<script type="text/javascript"> 
<xsl:for-each select="TestCaseDetails/TestCase">
var ctx = document.getElementById("myChart"); 
var myChart = new Chart(ctx, { 
type: 'pie', 
data: { 
    labels: ["Pass", "Fail"], 
    datasets: [ 
    { label: '# of students', 
        data: [<xsl:value-of select="PassCount" />,<xsl:value-of select="FailCount" />], 
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
						<th style="width:250px">StepName</th>
						<th style="width:250px">StepDescription</th>
						<th style="width:200px">TestData</th>
						<th style="width:50px">Status</th>
						<th style="width:150px">ExecutionTime</th>
					</tr>

					<xsl:for-each select="TestCaseDetails/Step">
						<tr>
							<td class="colfmt, results">
								<xsl:value-of select="SerialNo" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="StepName" />
							</td>
							<td class="colfmt, results" >
								<xsl:value-of select="StepDescription" />
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="TestData" />
							</td>
							<td class="colfmt, results">
							<a > 
							    <xsl:attribute name="href">./screenshots/<xsl:value-of select="Image"/></xsl:attribute>
								

								<!--<xsl:value-of select="Status" target = "Status" SRC="Status" img="/{@Status}"/>-->
								<!--<xsl:value-of select="Status" target = "Status" SRC="Status" img="$Status"/>-->
								<img WIDTH="30" HEIGHT="30"><xsl:attribute name="src" >./screenshots/<xsl:value-of select="Status" /></xsl:attribute></img>

								</a>
							</td>
							<td class="colfmt, results">
								<xsl:value-of select="ExecutionTime" />
							</td>
						</tr>

					</xsl:for-each>
				</table>
				
				<table >
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
			</table>
				
				</div>
			</body>
			
		</html>
	</xsl:template>
</xsl:stylesheet>