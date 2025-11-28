<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Dokumentum elemei</title>
            </head>
            <body>
                <h1>Hány elemből áll a dokumentum?</h1>
                <p>
                    <xsl:value-of select="count(//*)"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>