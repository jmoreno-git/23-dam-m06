<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>School data</title>
                <style>
                    table, th, tr, td {
                        border: 1px solid;
                        border-collapse: collapse;
                    }
                    tr:hover {background-color: coral;}
                    th {
                        text-transform: capitalize;
                        background-color: #04AA6D;
                        color: white;
                        font-size: 1.5em;
                    }
                    th, td {padding: 5px 10px 5px 10px;}
                    th.students {
                        background-color:orange; 
                        text-align:center;
                    }
                    th.group {
                        font-weight: bold; 
                        background-color:blue; 
                        text-align:center;
                    }
                    td.group {font-weight: bold;}
                    td.minor {color: red; font-style: italic;}
                </style>
            </head>
            <body>
                <xsl:apply-templates select="/school"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="school">
        <h2>
            <xsl:value-of select="name"/>
        </h2>
        <table>
            <xsl:for-each select="groups/group">
                <xsl:apply-templates select="."/>
            </xsl:for-each>
        </table>        
    </xsl:template>

    <xsl:template match="group">
        <tr>
            <th colspan="5" class="group">
                <xsl:value-of select ="local-name()"/>
            </th>
        </tr>
        <tr>
            <th>name</th>
            <th>tutor</th>
            <th colspan="3">curriculum</th>
        </tr>
        <tr>
            <td class="group">
                <xsl:value-of select="name"/>
            </td>
            <td class="group">
                <xsl:value-of select="tutor"/>
            </td>
            <td  class="group" colspan="3">
                <xsl:value-of select="curriculum"/>
            </td>
        </tr>
        <tr>
            <th colspan="5" class="students">
                <xsl:value-of select ="local-name(students)"/> (<xsl:value-of select ="count(students/student)"/>)
            </th>
        </tr>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
            <th>emails</th>
            <th>age</th>
        </tr>
        <xsl:for-each select="students/student">
            <xsl:apply-templates select="."/>
        </xsl:for-each>        
    </xsl:template>
    
    <xsl:template match="student">
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
            <xsl:choose>
                <xsl:when test="age &lt; 18">
                    <td class="minor"><xsl:value-of select="age"/></td>
                </xsl:when>
                <xsl:otherwise>
                    <td><xsl:value-of select="age"/></td>
                </xsl:otherwise>
            </xsl:choose>
        </tr>
    </xsl:template>

</xsl:stylesheet>
