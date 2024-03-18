const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = () => {
    const isDevMod = process.env.NODE_ENV === "dev";

    return {
        entry: "./src/main.tsx",
        output: {
            filename: "bundle.js",
            path: path.resolve(__dirname, "dist"),
            clean: true,
        },
        resolve: {
            extensions: [".ts", ".tsx", ".js", ".jsx"],
            alias: {
                // 절대경로 설정
            },
        },

        module: {
            rules: [
                //ts loader
                {
                    test: /\.(ts|tsx)$/,
                    use: {
                        loader: "ts-loader",
                        options: {
                            configFile: path.resolve(
                                __dirname,
                                "tsconfig.json"
                            ),
                        },
                    },
                },

                // js loader
                {
                    test: /\.(js|jsx)$/,
                    exclude: /node_modules/,
                    use: {
                        loader: "babel-loader",
                    },
                },

                // css(scss) loader
                {
                    test: /\.s[ac]ss$/,
                    use: ["style-loader", "css-loader", "sass-loader"],
                },

                // asset loader
                {
                    test: /\.(png|svg|jpg|jpeg|gif)$/i,
                    type: "asset/resource",
                },
            ],
        },
        plugins: [
            new HtmlWebpackPlugin({
                template: "public/index.html",
            }),
        ],
    };
};
