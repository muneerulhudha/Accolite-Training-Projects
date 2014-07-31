var croupOnApp = angular.module('croupOnApp', ['ui.router','ui.bootstrap','reCAPTCHA']);
var x;
var y;
var count=0;
var cart =[];

  croupOnApp.config(function (reCAPTCHAProvider) {

     // required, please use your own key :)
     reCAPTCHAProvider.setPublicKey('6Lcsx_cSAAAAAKEa3F1VC3VJGKy4gwdtbmN9-pQT');

     // optional
     reCAPTCHAProvider.setOptions({
         theme: 'clean'
     });
  });

  croupOnApp.config(function($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/home');

       $stateProvider
        
        .state('home', {
            url: '/home',
            templateUrl : 'templates/home.html',
            controller  : 'mainController'
        })

        .state('login',{
            url: '/login',
            templateUrl : 'templates/login.html',
            controller  : 'loginController'
        })

        .state('signup',{
            url:'/signup',
            templateUrl : 'templates/signup.html',
            controller  : 'signupController'
        })

        .state('corporatehome',{
            url:'/corporatehome',
            templateUrl : 'templates/corporatehome.html',
            controller  : 'corporateController'
        })

                .state('corporatehome.corphome',{
                    url:'/corphome',
                    templateUrl : 'templates/corphome.html',
                    controller  : 'corphomeController'
                })

                .state('corporatehome.addoffer',{
                    url:'/addoffer',
                    templateUrl : 'templates/addoffer.html',
                    controller  : 'addOfferController'
                })

                .state('corporatehome.viewoffer',{
                    url:'/viewoffer',
                    templateUrl : 'templates/viewoffer.html',
                    controller  : 'viewofferController'
                })

        .state('usermain',{
            url:'/usermain',
            templateUrl : 'templates/usermain.html',
            controller  : 'userController'
        })

            .state('usermain.userhome',{
                url:'/userhome',
                templateUrl : 'templates/userhome.html',
                controller  : 'userhomeController'
            })

            .state('usermain.useroffers',{
                url:'/useroffers',
                templateUrl : 'templates/useroffers.html',
                controller  : 'userofferController'
            })

            .state('usermain.usercart',{
                url:'/usercart',
                templateUrl : 'templates/usercart.html',
                controller  : 'usercartController'
            });

  });

  // create the controller and inject Angular's $scope


   croupOnApp.controller('userController', function($scope) {

    // create a message to display in our view
    //$scope.message = 'This is the HomePage';
  });

  croupOnApp.controller('userhomeController', ['$scope', '$http', function($scope, $http) {

    $scope.OfferList1 = {};
    $scope.OfferResponse1=[];
    $scope.OfferList1.hiddenValue=null;
    $scope.OfferList1.hiddenValue='7';
    $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.OfferList1
          }).success(function(data)
          {
              console.log("Result ");
              for(var i = 0; i < data.length; i++)
              {
                  $scope.OfferResponse1[i] = data[i];
                  console.log("Data-Offername "+$scope.OfferResponse1[i].OfferName);
              }        
          }).error(function(data){
            console.log('error');
    });

    $scope.cartPopulate=[];
    
   $scope.addToCart=function(OfferName,Description,Price,Participants,StartDate,EndDate)
   {
      cart[count]={};
      cart[count].OfferName=OfferName;
      cart[count].Desc=Description;
      cart[count].Price=Price;
      cart[count].Participants=Participants;
      cart[count].StartDate=StartDate;
      cart[count].EndDate=EndDate;
      console.log("Of Name:" +cart[count].OfferName);
      console.log("Count :"+count);
      count++;
      //cart=$scope.cartPopulate;
   }
   
  }]);

  croupOnApp.controller('userofferController',['$scope', '$http', function($scope, $http) {
   $scope.OfferList2 = {};
    $scope.OfferResponse2=[];
    $scope.OfferList2.hiddenValue=null;
    $scope.OfferList2.hiddenValue='8';
    $scope.OfferList2.UserName=x;
    $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.OfferList2
          }).success(function(data)
          {
              console.log("Result ");
              for(var i = 0; i < data.length; i++)
              {
                  $scope.OfferResponse2[i] = data[i];
                  console.log("Data-Offername "+$scope.OfferResponse2[i].OfferName);
              }        
          }).error(function(data){
            console.log('error');
    });
        }
  ]);

  croupOnApp.controller('usercartController',['$scope','$http', function($scope,$http) {
    $scope.cartList=[];
    $scope.cartList=cart;

    $scope.signOffer=function(ON,NP,SD,P)
    {
      $scope.OfferList3={};
      $scope.OfferList3.UserName=x;
      $scope.OfferList3.OfferName=ON;
      $scope.OfferList3.hiddenValue='9';
      $scope.OfferList3.Participants=NP;
      $scope.OfferList3.SignDate=SD;
      $scope.OfferList3.Price=P;
      $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.OfferList3
          }).success(function(data)
          {
              console.log("Result " +data.result);
              if(angular.equals(data.result,"false"))
              {
                $scope.boolTest=true;
              }
              else
              {
                $scope.boolTest=false;
                    
          }
        }).error(function(data){
            console.log('error');
    });

    }
    

  }]);
  croupOnApp.controller('mainController', ['$scope', function($scope) {

    // create a message to display in our view
    $scope.message = 'This is the HomePage';
  }]);

  croupOnApp.controller('loginController',['$scope','$http','$state',
  function($scope,$http,$state)
  {
    count=0;
    $scope.boolPass=false;
    $scope.signin=function()
    {
      $scope.login.hiddenValue='0';
      //alert("Login");
      $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.login
          }).success(function(data)
          {
            $scope.status=data.result;
            //alert(data);
            if(angular.equals(data.result,"true"))
            {
              x=$scope.login.userName;
              console.log("uName :"+x +" orignial: " +$scope.login.userName);
              console.log('Data received '+data.result +'user type='+data.userType);
              if(angular.equals(data.userType,"corporate"))
              {
                $state.go('corporatehome');
              }
              else
                $state.go('usermain');

            }
            else
            {
              console.log('Invalid user');
              $state.go('login');
              $scope.boolPass=true;
            }
            

        
          }).error(function(data){
            console.log('error');
          });


      
    };
    
  }]);

  croupOnApp.controller('signupController',['$scope','$http','$state',
  function($scope,$http,$state){
    
    $scope.master={};
    
    
    $scope.register=function()
    {
      if(angular.equals($scope.signup.password,$scope.signup.rpassword))
      {
        
          $scope.signup.hiddenValue='1';
          //alert("Register "+ $scope.signup.userType); 
          $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.signup
          }).success(function(data)
          {
            $scope.status=data;
            //alert(data);
            console.log('Data received');
            $state.go('login');
        
          }).error(function(data){
            console.log('error');
          });
          

      }
      else
      { 
        //alert("Password does not match");
      }
    };

    $scope.reset=function()
    {
      $scope.signup=angular.copy($scope.master);
    };
    $scope.checkPassword = function(a, b){
      //console.log('inside');
      if(a && b)
      {
        //console.log("a b");
        if(angular.equals(new String(a),new String(b)))
         {
          console.log("a and b are equal");
          $scope.boolFlag = false ;
        }
        else
          $scope.boolFlag = true ;  
      }
      
    };


}]);
  
  croupOnApp.controller('corporateController', function($scope, $location) {
    $scope.isActive = function (viewLocation) {
     var active = (viewLocation === $location.path());
     return active;
    };
  });

  croupOnApp.controller('corphomeController', ['$scope','$http',function($scope,$http) {
    // create a message to display in our view
   
  }]);

  croupOnApp.controller('addOfferController',['$scope','$http','$state', function($scope,$http,$state) 
  {
    $scope.addOffer=function()
    {
        console.log("Inside add offer");
        if(Date($scope.offer.offerStartDate) < Date())
        {
           console.log("Start date Invalid ");

        }
        else
        {

            console.log("Start date valid adding it to the database");
            $scope.offer.hiddenValue='2';
            $scope.offer.offerUser = x;
            $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.offer
          }).success(function(data)
          {
            $scope.status=data;
            $scope.offer={};
            //alert(data);
            $state.go('corporatehome.viewoffer');
        
          }).error(function(data){
            console.log('error');
          });

        }
         
         
    };
    $scope.checkDate=function()
    {
      //Date.prototype.yyyymmdd = 
      var a=new Date();
      var d;
      var yyyy = a.getFullYear().toString();
      var mm = (a.getMonth()+1).toString(); // getMonth() is zero-based
      var dd  = a.getDate().toString();
      d=yyyy + "-"+(mm[1]?mm:"0"+mm[0])+ "-"+ (dd[1]?dd:"0"+dd[0]); // padding
      

      console.log("D:" +d);

      $scope.boolFlag=false;

      console.log("Inside Check Date "+d);
        if($scope.offer.offerStartDate < d)
        {
          console.log("Date-"+  $scope.offer.offerStartDate   +" "+d);
          $scope.boolFlag=true;
         }
        else
        {
          $scope.boolFlag=false;
          console.log("Date- false: "+ $scope.offer.offerStartDate +" D:"+ d); 
        }
          
    };
    $scope.checkEndDate=function()
    {

      var a=new Date();
      var d;
      var yyyy = a.getFullYear().toString();
      var mm = (a.getMonth()+1).toString(); // getMonth() is zero-based
      var dd  = a.getDate().toString();
      d=yyyy + "-"+(mm[1]?mm:"0"+mm[0])+ "-"+ (dd[1]?dd:"0"+dd[0]); // padding
      
      $scope.boolFlag1=false;
      $scope.boolEnd=false;
      console.log("Inside Check Date");
       if($scope.offer.offerEndDate < d ) 
        {
            console.log("Date-"+  $scope.offer.offerEndDate   +" "+d);
            $scope.boolFlag1=true;
        }
        else 
        {
          $scope.boolFlag1=false;
          console.log("Date- false: "+ $scope.offer.offerEndDate +" D:"+ d); 
        } 
        if($scope.offer.offerEndDate < $scope.offer.offerStartDate)
          {
              console.log("Enddate < startDate" +$scope.offer.offerStartDate +" end date: "+$scope.offer.offerEndDate);
              $scope.boolEnd=true;
          }
          else
           {
              console.log("Enddate is fine");
              console.log("Else "+$scope.offer.offerStartDate+" end date: "+$scope.offer.offerEndDate);
              $scope.boolENd=false;
           }
          
    };
  }]);

  croupOnApp.controller('viewofferController',['$scope','$http','$state','$modal',function($scope,$http,$state,$modal) {

    // create a message to display in our view
    console.log("username :" +x);
    $scope.OfferList = {};
    $scope.OfferResponse=[];
    $scope.OfferList.user = x;
    console.log("username :" +$scope.OfferList.user);
    $scope.message = 'This is the Corporate HomePage';
    console.log("Inside corphome");
    $scope.OfferList.hiddenValue=null;
    $scope.OfferList.hiddenValue='3';
    $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.OfferList
          }).success(function(data)
          {
              console.log("Result ");
              for(var i = 0; i < data.length; i++)
              {
                  $scope.OfferResponse[i] = data[i];
                  console.log("Data-Offername "+$scope.OfferResponse[i].OfferName);
              }        
          }).error(function(data){
            console.log('error');
    });

    $scope.delete=function(a)
    {
        console.log("Offer to be deleted "+a);
        $scope.offerDelete={};
        $scope.offerDelete.hiddenValue='4';
        $scope.offerDelete.offerName=a;
        $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.offerDelete
          }).success(function(data)
          {
              console.log("Success ");
              //$state.reload();
              $state.go($state.current,{},{reload:true});     
          }).error(function(data){
            console.log('error');
      });
    }
    
    
    $scope.open = function (size,b) 
    { 
        y=b;
        var modalInstance = $modal.open({
        templateUrl: 'templates/myModalContent.html',
        size: size,
        
    });
      }
}]);


  croupOnApp.controller('modalController', ['$scope','$http', function($scope, $http){
      
      console.log("Y valiue :"+y);
      $scope.report={};
      $scope.result=[];
      $scope.report.hiddenValue='5';
      $scope.report.OfferName=y;
      $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.report
          }).success(function(data)
          {
              console.log("Success ");
              for(var i=0;i<data.length;i++)
              {
                  $scope.result[i]=data[i];
                  console.log("Data "+$scope.result[i].UserName);
                  console.log("Data "+$scope.result[i].Participants);
              }
              //$state.reload();
          }).error(function(data){
            console.log('error');
      });

          

          console.log($scope.result);
          
          $scope.report1={};
          $scope.remaining=null;
          $scope.report1.hiddenValue='6';
          $scope.report1.OfferName=y;
          $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/GroupOnServlets',
            data: $scope.report1
          }).success(function(data)
          {
              console.log("Success ");
              $scope.remaining=data.Remaining;
              console.log("Remianing : "+$scope.remaining);
              //$state.reload();
          }).error(function(data){
            console.log('error');
      });




  }])

  croupOnApp.directive('ngUnique', ['$http', function ($http) {
    return {

        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            elem.on('blur', function (evt) {
                scope.$apply(function () {                   
                    var val = elem.val();
                    var req = {"Email": val };
                    ctrl.$setValidity('unique',true);
                    $http({
            method:'POST',
            url:'http://localhost:8082/GroupOnAngularJSAssignment/Validity',
            data: req
          }).success(function(data, status, headers, config) {   
             // console.log(data.result);
              if(angular.equals(data.result,"false"))
              {
                ctrl.$setValidity('unique', false);
                //scope.signup.email={};
                console.log(data.result);
                //ctrl.$setValidity('unique',true);
                

              }
              });
                });
            });
        }
    }
}]);


