(function (ng) {
    var mod = ng.module("reservaModule");

        mod.controller("reservaCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext', 'sucursalContext', function ($scope, $state, $stateParams, $http, clienteContext, sucursalContext) {
            $scope.reservaContext = '/reservas';
            // inicialmente el listado de ciudades estÃ¡ vacio
            $scope.records = {};
            // carga las ciudades
            $http.get(clienteContext + "/" + $stateParams.clienteId + $scope.reservaContext).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibiÃ³ un cityId ??
            // revisa los parÃ¡metros (ver el :cityId en la definiciÃ³n de la ruta)
            if ($stateParams.reservaId !== null && $stateParams.reservaId !== undefined) {
                
                // toma el id del parÃ¡metro
                id = $stateParams.reservaId;
                // obtiene el dato del recurso REST
                $http.get(clienteContext + "/" + $stateParams.clienteId +$scope.reservaContext + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibiÃ³ un cityId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    sucursal: {}
                };
              
                $scope.alerts = [];
            }
            
            $http.get(sucursalContext).then(function(response) {
                $scope.sucursal =  response.data;
            });


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {
                    
                    // ejecuta POST en el recurso REST
                    return $http.post(clienteContext+ "/" + $stateParams.clienteId + $scope.reservaContext + "/sucursal/" + $stateParams.clienteId, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('reservaList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put($http.post(clienteContext + "/" + $stateParams.clienteId + $scope.reservaContext + "/" + currentRecord.id, currentRecord))
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('reservaList');
                        }, responseError);
                };
            };

           this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(clienteContext + "/" + $stateParams.clienteId + $scope.reservaContext +"/"+id)
                        .then(function () {
                            $scope.records = {};
                            $http.get(clienteContext).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('reservaList');
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
