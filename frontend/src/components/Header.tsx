import './Header.css';

function Header() {

    return (
        <header className="header">
            <img className="header-img" src="https://cdn-icons-png.flaticon.com/512/1163/1163650.png?w=1060&t=st=1679654057~exp=1679654657~hmac=d0b99f4eee472abac62d819679d8af628bcaaaaf7a82f40995be0746f0dea085"
                 alt="header image-1"/>
            <h1 className="header__title">Online Booklibrary</h1>
            <img className="header-img" src= "https://cdn-icons-png.flaticon.com/512/1187/1187533.png?w=1060&t=st=1679654376~exp=1679654976~hmac=852b2727021921c7f1ca1838e9470b245f9dc3c614ddc0a2f4d0ba1d11982820"/>
        </header>
    )

}

export default Header;