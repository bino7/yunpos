<!doctype html>
<html ng-app>
    <head>
        <script src="http://apps.bdimg.com/libs/angular.js/1.2.16/angular.min.js"></script>
    </head>
    <body>
        <div ng-controller="testCtrl">
            <b>Invoice:</b>
            <br>
            <br>
            <table>
                <tr><td>num1</td><td>num2</td></tr>
                <tr>
                    <td><input type="number" min="0" max="10" ng-model="number1" required ></td>
                    <td><input type="number" min="2" max="10" ng-model="number2" required ></td>
                </tr>
            </table>
            <hr>
            <b>Total:</b> {{number1 * number2 | currency}}
        </div>
        <script>
            function testCtrl($scope) {
                $scope.number1 = 2;
                $scope.number2 = 5.6;
            }
        </script>
    </body>
</html>