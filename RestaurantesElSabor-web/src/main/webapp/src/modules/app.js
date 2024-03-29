(function(ng){
    var app = ng.module('mainApp',[
        'ui.router',
        'ngMessages',
        'clienteModule',
        'domiciliosModule',
        'medioModule',
        'platoModule',
        'reservaModule',
        'sucursalModule',
        'mesaModule',
        'facturaModule'
    ]);
    app.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);
    app.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/RestauranteElSabor');
        }]);
})(window.angular);