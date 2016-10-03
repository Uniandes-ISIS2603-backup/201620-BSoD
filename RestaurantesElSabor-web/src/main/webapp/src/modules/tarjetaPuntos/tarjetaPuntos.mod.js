/* 
 * Modulo Cliente.
 */
(function(ng)
{
    var mod = ng.module("tarjetaPuntosModule",["ngMessages", "ui.router"] );
    mod.constant("tarjetaPuntosContext", "api/tarjetaPuntos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/tarjetaPuntos/';
  
            $stateProvider.state('tarjetaPuntosList', 
            {
                url: '/tarjetaPuntos',
                views: 
                        {
                            'mainView': 
                            {
                                controller: 'tarjetaPuntosCtrl',
                                controllerAs: 'tarjetaPuntosCtrl',
                                templateUrl: basePath + 'tarjetaPuntos.list.html'
                            }
                        }
            }).state('tarjetaPuntosCreate', 
            {
                url: '/tarjetaPuntos/create',
                views: 
                {
                    'mainView': 
                    {
                        controller: 'tarjetaPuntosCtrl',
                        controllerAs: 'tarjetaPuntosCtrl',
                        templateUrl: basePath + 'tarjetaPuntos.create.html'
                    }
                }

            }).state('tarjetaPuntosEdit', 
            {
                url: '/{tarjetaPuntosId:int}/edit',
                param: 
                {
                    tarjetaPuntosId: 'tarjetaPuntosId'
                },
                views: 
                {
                    'mainView': 
                    {
                        controller: 'tarjetaPuntosCtrl',
                        controllerAs: 'tarjetaPuntosCtrl',
                        templateUrl: basePath + 'tarjetaPuntos.edit.html'
                    }
                }
            });
        }]);
    
}
)(window.angular);


