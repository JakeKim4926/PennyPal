const { merge } = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common(), {
    mode: 'production',
    devtool: 'hidden-source-map',
    devServer: {
        port: 3000,
        historyApiFallback: true,
    },
    plugins: [],
    performance: false,
});
