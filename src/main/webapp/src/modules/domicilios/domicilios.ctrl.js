(function (ng) {
    var mod = ng.module("domiciliosModule");

    mod.controller("domiciliosCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext', function ($scope, $state, $stateParams, $http, clienteContext) {

            // inicialmente el listado de domicilios estÃ¡ vacio
            $scope.domicilioContext = '/domicilios';
            $scope.records = {};
            // carga domicilios
            $http.get(clienteContext + "/" + $stateParams.clienteId + $scope.domicilioContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibiÃ³ un domicilioId ??
            // revisa los parÃ¡metros (ver el :domicilioId en la definiciÃ³n de la ruta)
            if ($stateParams.domicilioId !== null && $stateParams.domicilioId !== undefined) {

                // toma el id del parÃ¡metro
                id = $stateParams.domicilioId;
                // obtiene el dato del recurso REST
                $http.get(clienteContext + "/" + $stateParams.clienteId +$scope.domicilioContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibiÃ³ un domicilioId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    direccion: '' /*Tipo String*/,
                    plato: '',
                    precio: undefined
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(clienteContext + "/" + $stateParams.clienteId + $scope.domicilioContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domiciliosList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(clienteContext + "/" + $stateParams.clienteId + $scope.domicilioContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('domiciliosList');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if (id != null)
                {
                    // ejecuta delete en el recurso REST
                    return $http.delete(clienteContext + "/" + $stateParams.clienteId + $scope.domicilioContext)
                            .then(function () {
                                $scope.records = {};
                                $http.get(context).then(function (response) {
                                    $scope.records = response.data;
                                }, responseError);
                                $state.go('domiciliosList');
                            }, responseError);
                }
            };

            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicaciÃ³n


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // FunciÃ³n showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);
