/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    var mod = ng.module("mesaModule",["ngMessages", "ui.router"] );
    mod.constant("mesaContext", "/mesa");
       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/mesa/';
            $urlRouterProvider.otherwise("/mesaList");
            
            $stateProvider.state('mesaList', {
                url: '/mesa',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesaCtrl',
                        controllerAs: 'mesaCtrl',
                        templateUrl: basePath + 'mesa.list.html'
                    }
                }
            }).state('mesaCreate', {
                url: '/mesa/create',
                parent: 'sucursalEdit',
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesaCtrl',
                        controllerAs: 'mesaCtrl',
                        templateUrl: basePath + 'mesa.create.html'
                    }
                }

            }).state('mesaEdit', {
                url: '/mesa/:mesaId',
                parent: 'sucursalEdit',
                param: {
                    mesaId: null
                },
                views: {
                    'sucursalInstanceView': {
                        controller: 'mesaCtrl',
                        controllerAs: 'mesaCtrl',
                        templateUrl: basePath + 'mesa.create.html'
                    }
                }
            });
        }]);
    
})(window.angular);


