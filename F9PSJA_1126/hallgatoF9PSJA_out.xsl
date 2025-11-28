<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <students>
            <xsl:for-each select="class/student">
                <student>
                    <id><xsl:value-of select="@id"/></id>
                    <vezeteknev><xsl:value-of select="vezeteknev"/></vezeteknev>
                    <keresztnev><xsl:value-of select="keresztnev"/></keresztnev>
                    <becenev><xsl:value-of select="becenev"/></becenev>
                    <kor><xsl:value-of select="kor"/></kor>
                    <osztondij><xsl:value-of select="osztondij"/></osztondij>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>