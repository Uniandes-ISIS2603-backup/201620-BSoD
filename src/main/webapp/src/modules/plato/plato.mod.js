/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    var mod = ng.module("platoModule",["ngMessages", "ui.router"] );
    mod.constant("platoContext", "api/platos");
       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/plato/';
     
            $stateProvider.state('platoList', {
                url: '/platos',
                views: {
                    'mainView': {
                        controller: 'platoCtrl',
                        controllerAs: 'platoCtrl',
                        templateUrl: basePath + 'plato.list.html'
                    }
                }
            }).state('platoCreate', {
                url: '/platos/create',
                views: {
                    'mainView': {
                        controller: 'platoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'plato.create.html'
                    }
                }

            }).state('platoEdit', {
                url: '/platos/:platoId',
                param: {
                    platoId: null
                },
                views: {
                    'mainView': {
                        controller: 'platoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'plato.create.html'
                    }
                }
            });
        }]);
    
})(window.angular);


