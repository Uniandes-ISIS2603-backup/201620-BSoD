(function (ng) {
    var mod = ng.module("domiciliosModule", ["ngMessages", "ui.router"]);
    mod.constant("domicilioContext", "api/domicilios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/domicilios/';
             $urlRouterProvider.otherwise("/domiciliosList");

            $stateProvider.state('domiciliosList', {
                url: '/domicilios',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'domiciliosCtrl',
                        templateUrl: basePath + 'domicilios.list.html'
                    }
                }
            }).state('domiciliosCreate', {
                url: '/domicilios/create',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'domiciliosCtrl',
                        templateUrl: basePath + 'domicilios.create.html'
                    }
                }

            }).state('domicilioEdit', {
                url: '/domicilios/:domicilioId',
                parent: 'clienteEdit',
                param: {
                    domicilioId: null
                },
                views: {
                    'clienteInstanceView': {
                        controller: 'domiciliosCtrl',
                        controllerAs: 'domiciliosCtrl',
                        templateUrl: basePath + 'domicilios.create.html'
                    }
                }
            });
        }]);
})(window.angular);
