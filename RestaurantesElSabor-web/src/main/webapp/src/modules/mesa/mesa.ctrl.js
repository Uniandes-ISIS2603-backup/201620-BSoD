(function (ng) {
    
    var mod = ng.module("mesaModule");

    mod.controller("mesaCtrl", ['$scope', '$state', '$stateParams', '$http', 'sucursalContext', function ($scope, $state, $stateParams, $http, sucursalContext) {

            // inicialmente el listado de mesa está vacio
            $scope.mesaContext = '/mesa';

            $scope.records = {};
            // carga las mesa
            $http.get(sucursalContext + "/" + $stateParams.sucursalId + $scope.mesaContext).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un mesaId ??
            // revisa los parámetros (ver el :mesaId en la definición de la ruta)
            if ($stateParams.mesaId !== null && $stateParams.mesaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.mesaId;
                // obtiene el dato del recurso REST
                $http.get(sucursalContext + "/" + $stateParams.sucursalId +$scope.mesaContext + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un mesaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    piso: undefined /*Tipo Integer*/,
                    cantSillas: undefined,
                    
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
             
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(sucursalContext + "/" + $stateParams.sucursalId + $scope.mesaContext, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('mesaList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    console.log(id);
                    console.log(currentRecord);
                    return $http.put(sucursalContext + "/" + $stateParams.sucursalId + $scope.mesaContext + "/" + id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('mesaList');
                        }, responseError);
                };
            };
            
            
             this.deleteRecord = function (id) { 
                   currentRecord = $scope.currentRecord;
                if(id!==null)
                {            
                   
                     // ejecuta delete en el recurso REST
                    return $http.delete(sucursalContext + "/" + $stateParams.sucursalId + $scope.mesaContext + "/" + id,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(sucursalContext + "/" + $stateParams.sucursalId + $scope.mesaContext).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('mesaList');
                        }, responseError); 
                 }
                        
              };

            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
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