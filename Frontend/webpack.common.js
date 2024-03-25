const path = require('path');
const webpack = require('webpack');
const dotenv = require('dotenv');
const CopyPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
dotenv.config();

module.exports = () => {
    return {
        entry: './src/main.tsx',
        output: {
            filename: 'bundle.js',
            path: path.resolve(__dirname, 'dist'),
            clean: true,
        },
        resolve: {
            extensions: ['.ts', '.tsx', '.js', '.jsx'],
            alias: {
                '@': path.resolve(__dirname, './src/'),
            },
        },
        module: {
            rules: [
                //ts loader
                {
                    test: /\.(ts|tsx)$/,
                    use: {
                        loader: 'ts-loader',
                        options: {
                            configFile: path.resolve(__dirname, 'tsconfig.json'),
                        },
                    },
                },

                // js loader
                {
                    test: /\.(js|jsx)$/,
                    exclude: /node_modules/,
                    use: {
                        loader: 'babel-loader',
                    },
                },

                // css(scss) loader
                {
                    test: /\.s[ac]ss$/,
                    use: ['style-loader', 'css-loader', 'sass-loader'],
                    include: path.resolve(__dirname, 'src'),
                },

                // asset loader
                {
                    test: /\.(png|svg|jpg|jpeg|gif)$/i,
                    type: 'asset/resource',
                },
            ],
        },
        plugins: [
            new HtmlWebpackPlugin({
                template: 'public/index.html',
            }),
            new webpack.DefinePlugin({
                'process.env': JSON.stringify(process.env),
            }),
            new CopyPlugin({ patterns: [{ from: 'public/assets', to: 'assets/' }] }),
        ],
    };
};
