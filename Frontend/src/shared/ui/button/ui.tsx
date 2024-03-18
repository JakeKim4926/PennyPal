type ButtonProps = {
    child: String;
};

export function Button({ child }: ButtonProps) {
    return <button>{child}</button>;
}
