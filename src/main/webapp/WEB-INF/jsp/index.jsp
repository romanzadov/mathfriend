<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="../css/MathFriend.css">

    <title>MathFriend</title>

    <script src="../js/jquery-1.7.2.min.js"></script>
    <script src="../js/jquery-ui-1.8.21.custom.min.js"></script>
    <script src="../js/jquery.ui.touch-punch.min.js"></script>
    <script type="text/javascript" language="javascript" src="../js/main.js"></script>
</head>
<body>
<noscript>
    <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
    </div>
</noscript>
<div id="operations">

</div>
<div id="sandbox">
    <h1>Math Fiend</h1>

    <div id="history-and-formula">

        <table align="center">
            <tr>
                <td id="nameFieldContainer">
                    <input type="text" />
                </td>
                <td id="sendButtonContainer">
                    <button id="#parse-button">calculate</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="color:red;" id="errorLabelContainer"></td>
            </tr>
        </table>
        <div id="formulaContainer">

        </div>
        <div id="operator">
            <div id="actionsContainer"></div>
        </div>
        <ol id="history">

        </ol>
        <div id="ghostContainer">

        </div>

    </div>

    <div id="droppedTermContainer"></div>
</body>
</div>
</html>

