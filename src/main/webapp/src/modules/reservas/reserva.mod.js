(function(ng){
    var mod = ng.module("reservaModule",["ngMessages","ui.router"] );
    mod.constant("reservaContext", "api/reservas");
       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservaList");            
     
            $stateProvider.state('reservaList', {
                url: '/reservas',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.list.html'
                    }
                }
            }).state('reservaCreate', {
                url: '/reservas/create',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.create.html'
                    }
                }

            }).state('reservaEdit', {
                url: '/reservas/:reservaId',
                parent: 'clienteEdit',
                param: {
                    reservaId: null
                },
                views: {
                    'clienteInstanceView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.create.html'
                    }
                }
            });
        }]);
    
})(window.angular);