/**
 * Created by rahulkumar on 05/03/17.
 */

'use strict';

app.controller('MainappCtrl', function ($scope,$http,$element,$q,$location,$mdDialog) {

    var canceller = $q.defer();
    $scope.uploadprogress = 0;
    $scope.errorData = "";
    $scope.isLoading = false;

    $scope.upload = function (ev) {
        var input = $element.find("input");
        var name = input.val();
        if(name.length != 0){
            console.log("File Name: ", name);
            var file = input[0].files[0];
            var dataForm = new FormData();
            dataForm.append('csvdata', file);
            $scope.isLoading = true;
            var postParams = {
                method: 'POST',
                url: '/upload',
                data: dataForm,
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity,
                uploadEventHandlers: {
                    progress: function (e) {
                        if (e.lengthComputable) {
                            $scope.uploadprogress = Math.round((e.loaded / e.total) * 100);
                        }
                    }
                },
            };

            var sendPost = $http(postParams);
            sendPost.then(function(response) {
                    console.log(response);
                    $scope.uploadprogress = 0;
                    document.getElementById('inputFile').value = null;
                    $scope.isLoading = false;
                },
                function(response) {
                    canceller
                },function(progress){
                    console.log('uploading: ' + Math.floor(progress) + '%');
                });
        }else {
            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#mainView')))
                    .clickOutsideToClose(true)
                    .title('File Upload Error !!')
                    .textContent('Please select a file to Upload.')
                    .ariaLabel('File Upload Error !!')
                    .ok('ok')
                    .targetEvent(ev)
            );
        }

    };
});
