##1.解决Parsing error: Unexpected token 'export'
在package.json的rule种加上"parser": "babel-eslint"

2.关闭语法检查
在vue.config.js中加lintOnSave:false