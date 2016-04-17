               var strategys = {
                   //是否为空
                   isNotEmpty: function(dom,errorMsg) {
//                       if(dom.val() == ''){   
//                           return {dom:dom,errorMsg:errorMsg};
//                       }else{
//                    	   return {dom:dom,errorMsg:''};
//                       }
                       if(dom.val() != '' && dom.val() !=null){   
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //限制最小长度
                   minLength: function(dom,length,errorMsg) {
                	   if(dom.val().length>=length){   
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //限制最大长度
                   maxLength: function(dom,length,errorMsg) {
                	   if(dom.val().length<length){   
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //手机号码格式
                   mobileFormat: function(dom,errorMsg) {
                	   reg = /(^1[3|5|8][0-9]{9}$)/;
                	   reg1 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                	   if(dom.val()==''){
                    	   errorMsg='电话号码不能为空!';
                       }
                       if(reg.test(dom.val()) || reg1.test(dom.val())) {
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //是否邮箱格式
                   isMail: function(dom,errorMsg) {
                       var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
                       if(dom.val()==''){
                    	   errorMsg='邮箱不能为空!';
                       }
                       if(reg.test(dom.val())){
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //是否汉字
                   isChinese: function(dom,errorMsg) {
                       var reg = /^[u4E00-u9FA5]+$/;
                       if(reg.test(dom.val())){
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },  
                 //是否链接
                   isURL: function(dom,errorMsg) {
                       var reg = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
                       if(dom.val() =='' ||reg.test(dom.val())){
                    	   errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   },
                   //是否两位小数正整数价格
                   isPrice: function(dom,errorMsg) {
                       var reg = /^\d+(?:\.\d{1,2})?$/;
                       if(dom.val() ==''){
                           errorMsg='价格不能为空!';
                       }
                       if(reg.test(dom.val())){
                           errorMsg='';
                       }
                       return {dom:dom,errorMsg:errorMsg};
                   }
               };
               
               var Validator = function(){
                   this.cache = [];  //保存校验规则
               };
               
               
               Validator.prototype.add = function(dom,rules){
            	   var self = this;
            	   for(var i = 0,rule;rule=rules[i++];){
            		   (function(rule){
            			   var strategyAry = rule.strategy.split(":");
            			   var errorMsg = rule.errorMsg;
            			   self.cache.push(function(){
            				   var strategy = strategyAry.shift();
            				   strategyAry.unshift(dom);
            				   strategyAry.push(errorMsg);
            				   return strategys[strategy].apply(dom,strategyAry);
            			   });
            		   })(rule);
            	   }
               };
               
//               Validator.addStrategys= (function(dom1,dom2,errorMsg){
//          			if(dom1.val()==dom2.val()){
//           				errorMsg='';
//           			}else{
//           				return{dom:dom2,errorMsg:errorMsg};
//           			}
//               },
//               'isEqual')
       
               
               
//               Validator.prototype.add = function(dom,rule,errorMsg) {
//                   var str = rule.split(":");
//                   //str返回的是类似 minLength:6 的规则
//                   this.cache.push(function(){
//                       var strategy = str.shift();
//                       str.unshift(dom.value); //把input的value添加进参数列表
//                       str.push(errorMsg); //把errorMsg添加进参数列表
//                       return strategys[strategy].apply(dom,str); 
//                   });
//               };
               
               Validator.prototype.start = function(dom){
                   var flag=true;
            	   for(var i = 0,validatorFunc;validatorFunc = this.cache[i++];){
                       var msg = validatorFunc();
                       if(dom){
                    	   if(msg.errorMsg){
                        	   if(dom == msg.dom[0]){
                        		   msg.dom.nextAll('.error-information').text(msg.errorMsg).show();
                        		   msg.dom.addClass("form-cerror");
                            	   return false;
                        	   }
                           }else{
                        	   msg.dom.nextAll('.error-information').hide();
                        	   msg.dom.removeClass("form-cerror");
                           }
                       }else{
                           if(msg.errorMsg){
                        	   msg.dom.nextAll('.error-information').text(msg.errorMsg).show();
                        	   msg.dom.addClass("form-cerror");
                        	   flag=false;
                           }else{
                        	   msg.dom.nextAll('.error-information').hide();
                        	   msg.dom.removeClass("form-cerror");
                           }
                       }
            	   }
            	   return flag;
               };
               
//               Validator.prototype.check = function(){
//            	   for(var i = 0,validatorFunc;validatorFunc = this.cache[i++];){
//                       var msg = validatorFunc();
//                       var flag=true;
//                       if(msg.errorMsg){
//                    	   msg.dom.next().text(msg.errorMsg).show();
//                    	   flag=false;
//                       }else{
//                    	   msg.dom.next().text(msg.errorMsg).hide();
//                       }
//            	   }
//            	   return flag;
//               };
               