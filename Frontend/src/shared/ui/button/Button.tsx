type ButtonProps = {
    child?: String;
    color?: String;
    size?: String;
    onClick?: React.MouseEventHandler<HTMLButtonElement>;
    disabled?: boolean;
};

export function Button({ child, color, size = 'medium', onClick, disabled }: ButtonProps) {
    return (
        <button className={['Button', size, color, disabled].join(' ')} onClick={onClick} disabled={disabled}>
            {child}
        </button>
    );
}
