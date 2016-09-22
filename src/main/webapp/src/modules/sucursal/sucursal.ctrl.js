(function (ng) {
    
    var mod = ng.module("sucursalModule");

    mod.controller("sucursalCtrl", ['$scope', '$state', '$stateParams', '$http', 'sucursalContext', function ($scope, $state, $stateParams, $http, sucursalContext) {

            // mensaje con la rspuesta de buscar mesas
            $scope.dato = '';

            // mensajes de error
            $scope.alerts = [];
            
            // listado de registros
            // al principio está vacio
            $scope.records = {};
            
            // pido el contenido de la lista
            // GET /sucursales
            $http.get(sucursalContext).then(function(response){
                // la respuesta coloquela en records
                $scope.records = response.data;    
            }, responseError);

            // si recibió parametros en la ruta
            //   sucursales/{sucursalId}
            if ($stateParams.sucursalId !== null && $stateParams.sucursalId !== undefined) {
                
                // tome el parametro de la ruta
                id = $stateParams.sucursalId;
               
                // pido el dato de esa sucursal
                //   GET /sucursales/{id}
                $http.get(sucursalContext + "/" + id)
                    .then(function (response) {
             
                        // la respuesta la coloca en "currentRecord"
                        $scope.currentRecord = response.data;
                    }, responseError);
         
            // si no tiene parametro
            } else
            {
                // coloca el current record vacio
                $scope.currentRecord = {
                    id: undefined,
                    ciudad: '',
                    direccion: '',
                    mesas: undefined,
                    calificacion: undefined,
                    /*error potencial*/
                    plato:{} /*Objeto que representa instancia de plato*/
                };
            }

            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
               
                if (id == null) {

                  
                    return $http.post(sucursalContext, currentRecord)
                        .then(function () {
                       
                            $state.go('sucursalList');
                        }, responseError);
                        
               
                } else {
                    
           
                    return $http.put(sucursalContext + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                   
                            $state.go('sucursalList');
                        }, responseError);
                };
            };
            
            // consulta las mesas disponibles en una fecha determinada
            this.mesasFecha = function (id, fecha){
                
                // fecha es de tipo Date
                
                // obtengo los datos por separado
                var dia = fecha.getDate();
                var mes = fecha.getMonth() + 1;
                var ano = fecha.getFullYear();
                
                // invoca el servicio
                //   GET  /sucursales/{id}/mesasDisponibles/{ano}/{mes}/{dia}
                return $http.get(sucursalContext 
                            + "/" + id 
                            + "/mesasDisponibles/" + ano + "/" + mes + "/" + dia )
                        .then (function (response){
                            
                            // al terminar, coloque la respuesta en dato
                            $scope.dato = response.data;                            
                            
                }, responseError);
            };

            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(sucursalContext + "/" + id,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(sucursalContext).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('sucursalList');
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