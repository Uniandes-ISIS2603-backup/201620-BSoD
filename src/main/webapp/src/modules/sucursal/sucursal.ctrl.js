(function (ng) {
    
    var mod = ng.module("sucursalModule");

    mod.controller("sucursalCtrl", ['$scope', '$state', '$stateParams', '$http', 'sucursalContext', function ($scope, $state, $stateParams, $http, sucursalContext) {

           
            $scope.records = {};
            
            $http.get(sucursalContext).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            
            if ($stateParams.sucursalId !== null && $stateParams.sucursalId !== undefined) {
                
              
                id = $stateParams.sucursalId;
               
                $http.get(sucursalContext + "/" + id)
                    .then(function (response) {
             
                        $scope.currentRecord = response.data;
                    }, responseError);

         
            } else
            {
                $scope.currentRecord = {
                    id: undefined,
                    ciudad: '',
                    direccion: '',
                    mesas: undefined,
                    calificacion: undefined,
                    /*error potencial*/
                    plato:{} /*Objeto que representa instancia de plato*/
                };
              
                $scope.alerts = [];
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
            
            this.mesasFecha = function (id, fecha){
                return $http.get(sucursalContext +"/"+id +"/mesasDisponibles/"+ fecha)
                        .then (function (){
                            $state.go('');
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