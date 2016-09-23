/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    var mod = ng.module("platoModule",["ngMessages", "ui.router"] );
    mod.constant("platoContext", "/plato");
       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/plato/';
            $urlRouterProvider.otherwise("/platoList");
             
            $stateProvider.state('platoList', {
                url: '/plato',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platoCtrl',
                        controllerAs: 'platoCtrl',
                        templateUrl: basePath + 'plato.list.html'
                    }
                }
            }).state('platoCreate', {
                url: '/plato/create',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'platoCtrl',
                        controllerAs: 'platoCtrl',
                        templateUrl: basePath + 'plato.create.html'
                    }
                }

            }).state('platoEdit', {
                url: '/plato/:platoId',
                parent: 'sucursalEdit',
                param: {
                    platoId: null
                },
                views: {
                    'sucursalInstanceView': {
                        controller: 'platoCtrl',
                        controllerAs: 'platoCtrl',
                        templateUrl: basePath + 'plato.create.html'
                    }
                }
            });
        }]);
    
})(window.angular);


