<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Drága autók</title>
            </head>
            <body>
                <h1>Hány autó drágább mint 30000?</h1>
                <p>
                    <xsl:value-of select="count(autok/auto[number(ar) > 30000])"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>