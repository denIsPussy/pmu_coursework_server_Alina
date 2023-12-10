import { NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import logo from "../image/logo.png"

export default function Header(props) {
    return (
      <div>

      <div className="header">
        <header className="hat" id="header">
          <nav className="navbar navbar-expand-lg" posi style={{backgroundColor: '#848484'}}>
            <div className="container">
              <div className="navbar-brand"><img src={logo}  width={190} height={100} /></div>
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navContent" aria-controls="navContent" aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon" /></button>
              <div className="collapse navbar-collapse" id="navContent">
                <ul className="navbar-nav">
                {
                                props.links.map(route =>
                                <li key={route.path}
                                    className="nav-item">
                                    <NavLink className="nav-link" to={route.path}>
                                            {route.label}
                                    </NavLink>
                                </li>
                                )
                            }
                </ul>
              </div>
            </div>
          </nav>
        </header>
      </div>
      </div>
        
    );
}