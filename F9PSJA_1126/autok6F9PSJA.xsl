<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Városonkénti statisztika</title>
            </head>
            <body>
                <h1>Városonként mennyi az ottani autók darabszáma és összára</h1>
                <table border="2" cellpadding="10">
                    <tr>
                        <th>Város</th>
                        <th>Darabszám</th>
                        <th>Összár</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <xsl:variable name="varos" select="tulaj/varos"/>
                        <xsl:if test="not(preceding-sibling::auto[tulaj/varos=$varos])">
                            <xsl:variable name="darab" select="count(../auto[tulaj/varos=$varos])"/>
                            <xsl:variable name="osszar" select="sum(../auto[tulaj/varos=$varos]/ar)"/>
                            <tr>
                                <td><xsl:value-of select="$varos"/></td>
                                <td><xsl:value-of select="$darab"/></td>
                                <td><xsl:value-of select="$osszar"/></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>