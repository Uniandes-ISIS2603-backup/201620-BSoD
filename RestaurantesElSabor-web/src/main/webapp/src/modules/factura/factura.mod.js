(function (ng) {
    var mod = ng.module("facturaModule", ["ngMessages", "ui.router"]);
    mod.constant("facturaContext", "api/facturas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/facturas/';
             $urlRouterProvider.otherwise("/facturaList");

            $stateProvider.state('facturaList', {
                url: '/facturas',
                parent: 'sucursalEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'facturaCtrl',
                        templateUrl: basePath + 'factura.list.html'
                    }
                }
            }).state('facturaCreate', {
                url: '/facturas/create',
                parent: 'sucursalEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'facturaCtrl',
                        templateUrl: basePath + 'factura.create.html'
                    }
                }

            });
        }]);
})(window.angular);


