/* 
 * Modulo Medio.
 */
(function(ng)
{
    var mod = ng.module("medioModule",["ngMessages", "ui.router"] );
    mod.constant("medioContext", "api/medios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/medio/';
  
            $stateProvider.state('medioList', 
            {
                url: '/medios',
                views: 
                        {
                            'mainView': 
                            {
                                controller: 'medioCtrl',
                                controllerAs: 'medioCtrl',
                                templateUrl: basePath + 'medio.list.html'
                            }
                        }
            }).state('medioCreate', 
            {
                url: '/medios/create',
                views: 
                {
                    'mainView': 
                    {
                        controller: 'medioCtrl',
                        controllerAs: 'medioCtrl',
                        templateUrl: basePath + 'medio.create.html'
                    }
                }

            }).state('medioEdit', 
            {
                url: 'medios/{medioId:int}/edit',
                param: 
                {
                    //  TODO: AVERIGUAR COMO SE PASAN PARAMETROS
                    medioId: 'medioId'
                },
                views: 
                {
                    'mainView': 
                    {
                        controller: 'medioCtrl',
                        controllerAs: 'medioCtrl',
                        templateUrl: basePath + 'medio.edit.html'
                    }
                }
            });
        }]);
    
}
)(window.angular);


