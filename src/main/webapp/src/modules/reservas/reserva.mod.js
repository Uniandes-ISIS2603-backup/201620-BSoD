/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function(ng){
    var mod = ng.module("reservaModule",["ngMessages","ui.router"] );
    mod.constant("reservaContext", "api/reservas");
       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
     
            $stateProvider.state('reservaList', {
                url: '/reservas',
                views: {
                    'mainView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.list.html'
                    }
                }
            }).state('reservaCreate', {
                url: '/reservas/create',
                views: {
                    'mainView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.create.html'
                    }
                }

            }).state('reservaEdit', {
                url: '/reservas/:reservaId',
                param: {
                    platoId: null
                },
                views: {
                    'mainView': {
                        controller: 'reservaCtrl',
                        controllerAs: 'reservaCtrl',
                        templateUrl: basePath + 'reserva.create.html'
                    }
                }
            });
        }]);
    
})(window.angular);