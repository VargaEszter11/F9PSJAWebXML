<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Hallgatók adatai</title>
            </head>
            <body bgcolor="#f5f5f5">
                <h1 align="center">Hallgatók adatai - for-each, value-of</h1>
                <table border="2" width="100%" bgcolor="white" cellpadding="10" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Vezeteknev</th>
                            <th>Keresztnev</th>
                            <th>Becenev</th>
                            <th>Kor</th>
                            <th>Osztondij</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="class/student">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="vezeteknev"/></td>
                                <td><xsl:value-of select="keresztnev"/></td>
                                <td><xsl:value-of select="becenev"/></td>
                                <td><xsl:value-of select="kor"/></td>
                                <td><xsl:value-of select="osztondij"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>