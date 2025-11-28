<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>F9PSJA Órarend</title>
            </head>
            <body bgcolor="#f5f5f5">
                <h1 align="center">F9PSJA Órarend</h1>
                <table border="2" width="100%" bgcolor="white" cellpadding="10" cellspacing="0">
                    <thead>
                        <tr>
                            <th bgcolor="#FCDBF5" align="left">ID</th>
                            <th bgcolor="#FCDBF5" align="left">Típus</th>
                            <th bgcolor="#FCDBF5" align="left">Tárgy</th>
                            <th bgcolor="#FCDBF5" align="left">Nap</th>
                            <th bgcolor="#FCDBF5" align="left">Időpont</th>
                            <th bgcolor="#FCDBF5" align="left">Helyszín</th>
                            <th bgcolor="#FCDBF5" align="left">Oktató</th>
                            <th bgcolor="#FCDBF5" align="left">Szak</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="F9PSJA_orarend/ora">
                            <xsl:sort select="idopont/nap"/>
                            <xsl:sort select="idopont/tol"/>
                            <tr>
                                <xsl:choose>
                                    <xsl:when test="@tipus='eloadas'">
                                        <xsl:attribute name="bgcolor">#EEFDFF</xsl:attribute>
                                    </xsl:when>
                                    <xsl:when test="@tipus='gyakorlat'">
                                        <xsl:attribute name="bgcolor">#FFFAF2</xsl:attribute>
                                    </xsl:when>
                                </xsl:choose>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="@tipus"/></td>
                                <td><xsl:value-of select="targy"/></td>
                                <td><xsl:value-of select="idopont/nap"/></td>
                                <td><b><font color="#3D002E"><xsl:value-of select="idopont/tol"/> - <xsl:value-of select="idopont/ig"/></font></b></td>
                                <td><xsl:value-of select="helyszin"/></td>
                                <td><xsl:value-of select="oktato"/></td>
                                <td><xsl:value-of select="szak"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>