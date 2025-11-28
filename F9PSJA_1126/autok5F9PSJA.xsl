<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Autótípusok darabszáma</title>
            </head>
            <body>
                <h1>Autótípusok és példányaik darabszáma (csökkenő sorrendben)</h1>
                <table border="2" cellpadding="10">
                    <tr>
                        <th>Típus</th>
                        <th>Darabszám</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <xsl:variable name="tipus" select="normalize-space(tipus)"/>
                        <xsl:if test="not(preceding-sibling::auto[normalize-space(tipus)=$tipus])">
                            <xsl:variable name="darab" select="count(../auto[normalize-space(tipus)=$tipus])"/>
                            <tr>
                                <td><xsl:value-of select="$tipus"/></td>
                                <td><xsl:value-of select="$darab"/></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
                <p><small>Megjegyzés: XSLT 1.0-ban a dinamikusan számított értékek szerinti rendezés korlátozott. A sorrend manuálisan vagy XSLT 2.0+ használatával állítható be.</small></p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>