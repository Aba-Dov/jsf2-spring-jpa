<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Email Template - Classic</title>
</head>
<body marginheight="0" topmargin="0" marginwidth="0" style="background-color: #f7f2e4; margin: 0px;" bgcolor="#f7f2e4" leftmargin="0">
<style type="text/css">
a:hover { text-decoration: underline !important; }
></style>
<!--100% body table--><table cellspacing="0" border="0" cellpadding="0" width="100%" bgcolor="#f7f2e4"><tr>
<td>
				<!--top links-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td valign="middle" align="center" height="45">
							<p style="font-size: 14px; line-height: 24px; font-family: Georgia, 'Times New Roman', Times, serif; color: #b0a08b; margin: 0px;">
								Is this email not displaying correctly? <webversion style="color: #bc1f31; text-decoration: none;">Try the web version.</webversion></p>
</td>
							</tr></table>
<!--header--><table style="background-image: url(${baseUrl}/images/header-bg.jpg); background-repeat: no-repeat; background-position: center; background-color: #fffdf9;" width="684" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#fffdf9"><tr>
<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td valign="top" width="173">
												<!--ribbon-->
												<table border="0" cellspacing="0" cellpadding="0"><tr>
<td height="120" width="45"></td>
														<td background="${baseUrl}/images/ribbon.jpg" valign="top" bgcolor="#c72439" height="120" width="80">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td valign="bottom" align="center" height="35">
																		<p style="font-size: 14px; font-family: Georgia, 'Times New Roman', Times, serif; color: #ffffff; margin-top: 0px; margin-bottom: 0px;">ISSUE</p>
																	</td>
																</tr>
<tr>
<td valign="top" align="center">
																		<singleline label="Title"><p style="font-size: 36px; font-family: Georgia, Times, serif; color: #ffffff; margin-top: 0px; margin-bottom: 0px; text-shadow: 1px 1px 1px #333;">31</p></singleline>
</td>
																</tr>
</table>
</td>
													</tr></table>
<!--ribbon-->
</td>
											<td valign="middle" width="493">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td height="60"></td>
												</tr>
<tr>
<td>
														<singleline label="Title"><h1 style="color: #333 !important; margin-top: 0px; margin-bottom: 0px; font-weight: normal; font-size: 48px; font-family: Georgia, Times, serif;">ABC <em>Newsletter</em>
</h1></singleline>
</td>
												</tr>
<tr>
<td height="40">
													</td>
												</tr>
</table>
<!--date--><table border="0" cellspacing="0" cellpadding="0"><tr>
<td valign="top" align="center" bgcolor="#312c26" background="${baseUrl}/images/date-bg.jpg" width="357" height="42">
														<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="5"></td></tr></table>
<p style="font-size: 24px; font-family: Georgia,  Times, serif; color: #ffffff; margin-top: 0px; margin-bottom: 0px;"><currentmonthname></currentmonthname><currentyear></currentyear></p>
													</td>
												</tr></table>
<!--/date-->
</td>
										<td width="18"></td>
									</tr></table>
</td>
						</tr></table>
<!--/header--><!--email container--><table bgcolor="#fffdf9" cellspacing="0" border="0" align="center" cellpadding="30" width="684"><tr>
<td>
									<!--email content-->
									<table cellspacing="0" border="0" id="email-content" cellpadding="0" width="624"><tr>
<td>
												<!--section 1-->
												<table cellspacing="0" border="0" cellpadding="0" width="100%"><tr>
<td valign="top" align="center">
															<h1 style="font-size: 32px; font-family: Georgia, Times, serif; color: #4e3227 !important; margin-top: 0px; margin-bottom: 0px; font-weight: normal;"><em>In This Issue</em></h1>
<br><a href="${newsSourceUrl}"><em>${newsSourceName}</em></a>
															<img style="display: block; margin: 0; padding: 0;" src="${baseUrl}/images/line-break-3.jpg">
															<#list headerTitles?keys as title>
																<tableofcontents>
																		<p style="margin-top: 0px; margin-bottom: 0px; font-size: 17px; color: #bc1f31; font-family: Georgia, Times, serif;">
																			<a href="${headerTitles[title]}">${title}</a>
																		</p>
																		<img style="display: block; margin: 0; padding: 0;" src="${baseUrl}/images/line-break-3.jpg">
																</tableofcontents>
															</#list>
														</td>
													</tr></table>
<!--/section 1--><!--section 3--><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td height="30"></td>
												</tr></table>
<table cellspacing="0" border="0" cellpadding="0" width="100%"><tr>
<td valign="top" width="39%">

														<table width="220" border="0" align="left" cellpadding="0" cellspacing="0">
<tr>
<td>
																	<table align="left" width="220" border="0" cellspacing="0" cellpadding="0"><tr>
<td bgcolor="#312c26" valign="top" style="background-color: #312c26; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px; -khtml-border-radius: 4px;">
																			<table width="100%" border="0" cellspacing="0" cellpadding="20"><tr>
<td>
																						<p style="font-size: 16px; font-family: Georgia, 'Times New Roman', Times, serif; color: #ffffff; margin-top: 0px; margin-bottom: 0px; font-weight: bold;">Forward to a friend</p>
																						<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="10"></td>
																							</tr></table>
<p style="color: #ffffff; margin-top: 0px; margin-bottom: 12px; font-size: 16px; font-family: Georgia, 'Times New Roman', Times, serif; padding: 0;">
																							Know someone who might be interested in our monthly newsletter?</p>
																							<p style="color: #ffffff; font-size: 16px; font-family: Georgia, 'Times New Roman', Times, serif; margin: 0px; padding: 0;"><forwardtoafriend style="color: #ffffff; text-decoration: underline;">Forward this email</forwardtoafriend> their way and help spread the word.</p>
																						</td>
																					</tr></table>
</td>
																		</tr></table>
</td>
															</tr>
<tr>
<td>
																</td>
															</tr>
<tr>
<td valign="top">
																		<!--break-->
																		<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td height="40"></td>
																			</tr></table>
<!--/break--><h2 style="font-size: 18px; font-family: Georgia, Times, serif; color: #333333 !important; margin-top: 0px; margin-bottom: 0px; font-weight: bold;">Unsubscribe</h2>
																		<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="10"></td>
																			</tr></table>
<multiline label="Description"><p style="font-size: 16px; line-height: 18px; font-family: Georgia, Times, serif; color: #333; margin-top: 0px; margin-bottom: 0px;">Do not want to receive these emails anymore?.</p></multiline><table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="10"></td>
																			</tr></table>
<table width="140" border="0" cellspacing="0" cellpadding="10"><tr>
<td style="background-color: #bc1f31; text-decoration: none; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px; -khtml-border-radius: 4px; padding: 5px;" bgcolor="#bc1f31">
																					<p style="margin-top: 0px; margin-bottom: 0px; font-size: 16px; font-family: Georgia, 'Times New Roman', Times, serif; text-align: center; line-height: 18px; color: #333;" align="center"><a style="color: #ffffff; text-decoration: none;" href="${unsubscribeUrl}">Unsubscribe</a></p>
</td>
																				</tr></table>
</td>
															</tr>
<tr>
<td valign="top">
																			<!--break-->
																			<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td height="30"></td>
																				</tr></table>
<!--/break--><repeater><h2 style="font-size: 18px; font-family: Georgia, Times, serif; color: #333333 !important; margin-top: 0px; margin-bottom: 0px; font-weight: bold;"><singleline label="Title" repeatertitle="true">Enter title here</singleline></h2>
																			<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="10"></td>
																				</tr></table>
<multiline label="Description"><p style="font-size: 16px; line-height: 18px; font-family: Georgia, Times, serif; color: #333; margin-top: 0px; margin-bottom: 0px;">Enter description text here.</p></multiline><table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="30"></td>
																				</tr></table></repeater>
</td>
															</tr>
</table>
</td>
													<td valign="top" width="61%">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
																	<img src="${baseUrl}/images/line-break-4.jpg" width="378" height="27">
</td>
															</tr>
<tr>
<td align="center">
																	<p style="font-size: 24px; line-height: 22px; font-family: Georgia, Times, serif; color: #bc1f31; font-weight: bold; margin: 0px;"><singleline label="Title">Latest Articles</singleline></p>
																</td>
															</tr>
<tr>
<td valign="top" height="40"><img src="${baseUrl}/images/line-break-5.jpg" width="378" height="17"></td>
															</tr>
</table>
<repeater>
<#list latestArticles as article>
	<!-- Articles here -->
	
	<h1 style="font-size: 24px; font-family: Georgia, Times, serif; color: #333333 !important; margin-top: 0px; margin-bottom: 12px;"><singleline label="Title" repeatertitle="true">${article.title}</singleline></h1>
															<!-- Check if article.image is null -->
															<#if article.image??>
																<img src="${article.image}" style="box-shadow: 2px 2px 6px #333; -webkit-box-shadow: 2px 2px 6px #333333; -khtml-box-shadow: 2px 2px 6px #333; -moz-box-shadow: 2px 2px 6px #333; border: 1px solid #fff;" width="370" height="178" editable="true" label="Image">
															</#if>
															<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
<td height="10"> </td>
																</tr></table>
<multiline label="Description"><p style="font-size: 16px; line-height: 22px; font-family: Georgia, Times, serif; color: #333; margin: 0px;">
																		${article.description}
																</p>
															</multiline><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
<td><img src="${baseUrl}/images/line-break-6.jpg" width="378" height="40"></td>
																</tr></table>
	</#list>	
</repeater>
</td>
											</tr></table>
<!--/section 3--><!--line break--><table cellspacing="0" border="0" cellpadding="0" width="100%"><tr>
<td height="72"><img src="${baseUrl}/images/line-break-2.jpg" width="622" height="72"></td>
											</tr></table>
<!--/line break-->
</td>
										</tr></table>
<!--/email content-->
</td>
							</tr></table>
<!--/email container--><!--footer--><table width="680" border="0" align="center" cellpadding="30" cellspacing="0">
<tr>
<td valign="top">
								<p style="font-size: 14px; line-height: 24px; font-family: Georgia, 'Times New Roman', Times, serif; color: #b0a08b; margin: 0px;">
									<singleline label="Title">You are receiving this newsletter because you subscribed to our newsletter.</singleline><br> 
									Not interested anymore? <unsubscribe style="color: #bc1f31; text-decoration: none;">Unsubscribe.</unsubscribe></p>
								</td>
								<td valign="top" width="245"><multiline label="Description"><p style="font-size: 14px; line-height: 24px; font-family: Georgia, Times, serif; color: #b0a08b; margin: 0px;">ABCWidgets Corp - 123 Some Street, City, ST 99999. ph +1 4 1477 89 745.</p></multiline></td>
								</tr>
<tr>
<td height="30"></td>
									<td height="30"></td>
								</tr>
</table>
<!--/footer-->
</td>
			</tr></table>
<!--/100% body table-->
</body>
</html>
