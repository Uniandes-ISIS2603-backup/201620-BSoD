/* 
 * Controlador Cliente.
 */
(function(ng)
{
    var mod = ng.module("clienteModule");
    
    mod.controller("clienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext',
        function ($scope, $state, $stateParams, $http, clienteContext) 
    {
        $scope.records = {};                            // La lista de clientes no contiene ninguno.
        
        $http.get(clienteContext).then(function(response)      // Obtiene los clientes del sistema GET.
        {
            $scope.records = response.data;    
        }, responseError);
        
        if ($stateParams.clienteId !== null && $stateParams.clienteId !== undefined) 
        {
                id = $stateParams.clienteId;                    // Toma el parametro id.
                
                $http.get(clienteContext + "/" + id)             // Obtiene el dato del recurso REST
                    .then(function (response) 
                    {  
                        $scope.currentRecord = response.data;    // Comando para actualizar el reccord que llega.
                    }, responseError);
        } 
        else
        {
            // Ajusta el record actual como un default.
            $scope.currentRecord = 
            {
                id: undefined,
                documentoIdentidad: undefined,
                tipoDocumentoIdentidad: ' ',
                name: undefined,
                apellidos: undefined,
                direccion: undefined,
                telefono: undefined,
                tarjetaPuntos: undefined,
                mediosPago: undefined
            };

            $scope.alerts = [];
        }
        
         this.agregarCliente = function () 
        {
            currentRecord = $scope.currentRecord;        
            if(currentRecord.id==null)
            {   
                return $http.post(clienteContext, currentRecord).then(function() 
                    {
                        $state.go('clienteList');
                    }, responseError);
            }
            else
            {
                return $http.put(clienteContext, currentRecord).then(function() 
                    {
                        $state.go('clienteList');
                    }, responseError);
            }
             
        };
        
        this.eliminarCliente = function(id)
        {
            return $http.delete(clienteContext+"/"+ id).then(function() 
            {
                $state.reload();
            }, responseError);
        };
        
        this.agregarTarjetaPuntosCliente = function(id)
        {
            return $http.post(clienteContext+"/"+ id+"/tarjetaPuntos").then(function() 
            {
                $state.reload();
            }, responseError);
        };

        this.eliminarTarjetaPuntosCliente = function(id)
        {
            return $http.delete(clienteContext+"/"+ id+"/tarjetaPuntos").then(function() 
            {
                $state.reload();
            }, responseError);
        };
        
        this.sumarPuntosTarjetaPuntosCliente = function(id, pCompra)
        {
            return $http.put(clienteContext+"/"+ id+"/tarjetaPuntos/sumaPuntos?compra="+pCompra).then(function() 
            {
                $state.go('clienteList');
            }, responseError);
        };
        
         
     // -----------------------------------------------------------------
     // Funciones para manejra los mensajes en la aplicación


    //Alertas
    this.closeAlert = function (index) 
    {
        $scope.alerts.splice(index, 1);
    };

    // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
    function showMessage(msg, type) 
    {
        var types = ["info", "danger", "warning", "success"];
        if (types.some(function (rc) 
        {
            return type === rc;
        })) 
        {
            $scope.alerts.push({type: type, msg: msg});
        }
    }

    this.showError = function (msg) 
    {
        showMessage(msg, "danger");
    };

    this.showSuccess = function (msg) 
    {
        showMessage(msg, "success");
    };

    var self = this;
            
    function responseError(response)
    {
        self.showError(response.data);
            
    }
    
}]);

    

})(window.angular);
    
    