<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>School data</title>
            </head>
            <body>
                <h2>
                    <xsl:value-of select="/school/name"/>
                </h2>
                <table border="1">
                    <xsl:for-each select="/school/groups/group">
                        <tr>
                            <th colspan="5" style="background-color:cyan; text-align:center;">Group</th>
                        </tr>
                        <tr>
                            <th>name</th>
                            <th>tutor</th>
                            <th colspan="3">curriculum</th>
                        </tr>
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="tutor"/>
                            </td>
                            <td colspan="3">
                                <xsl:value-of select="curriculum"/>
                            </td>
                        </tr>
                        <tr>
                            <th  colspan="5" style="background-color:yellow; text-align:center;">Students</th>
                        </tr>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>surname</th>
                            <th>emails</th>
                            <th>age</th>
                        </tr>
                        <xsl:for-each select="students/student">
                            <tr>
                                <td>
                                    <xsl:value-of select="@id"/>
                                </td>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="surname"/>
                                </td>
                                <td>
                                    <ul>
                                        <xsl:for-each select="emails/email">
                                            <li>
                                                <xsl:value-of select="."/>
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </td>
                                <td>
                                    <xsl:value-of select="age"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
