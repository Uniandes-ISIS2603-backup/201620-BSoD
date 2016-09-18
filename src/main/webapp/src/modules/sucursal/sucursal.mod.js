(function (ng) {
    var app = ng.module("sucursalModule", ["ngMessages","ui.router"]);
   
    app.constant("sucursalContext", "api/sucursal");
    app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
           
            var basePath = 'src/modules/sucursal/';
     
            $stateProvider.state('sucursal', {
                url: '/sucursal',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'sucursalCtrl',
                        controllerAs: 'sucursalCtrl',
                        templateUrl: basePath + 'sucursal.html'
                    }
                }
            }).state('sucursalList', {
                url: '/list',
                parent: 'sucursal',
                views: {
                    'sucursalView': {
                        controller: 'sucursalCtrl',
                        controllerAs: 'sucursalCtrl',
                        templateUrl: basePath + 'sucursal.list.html'
                    }
                }

            }).state('sucursalCreate', {
                url: '/create',
                parent: 'sucursal',
                views: {
                    'sucursalView': {
                        controller: 'sucursalCtrl',
                        controllerAs: 'sucursalCtrl',
                        templateUrl: basePath + 'sucursal.create.html'
                    }
                }

            }).state('sucursalEdit', {
                url: '/{:sucursalId}/edit',
                param: {sucursalId: null},
                parent: 'sucursal',
                views: {
                    'sucursalView': {
                        controller: 'sucursalCtrl',
                        controllerAs: 'sucursalCtrl',
                        templateUrl: basePath + 'sucursal.create.html'
                    },
                    'childSucursalView':{
                        templateUrl: basePath + 'sucursal.instance.html'
                    }
                    
                }
            });
        }]);
})(window.angular);