// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TreeStoneBlue;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;

import static edu.wpi.first.wpilibj2.command.Commands.runOnce;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final XRPDrivetrain drivetrain = new XRPDrivetrain();
    private final XRPArm arm = new XRPArm();

    private final CommandXboxController controller = new CommandXboxController(0);

    private final SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * Joystick} or {@link XboxController}), and then passing it to a {@link
     * JoystickButton}.
     */
    private void configureButtonBindings() {
        drivetrain.setDefaultCommand(getTankDriveCommand());

        m_chooser.setDefaultOption("Tree Stone Blue", new TreeStoneBlue(drivetrain, arm));
        SmartDashboard.putData(m_chooser);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }

    public Command getArcadeDriveCommand() {
        return new ArcadeDrive(drivetrain, controller::getLeftY, () -> -controller.getLeftX());
    }

    public Command getTankDriveCommand() {
        return new TankDrive(drivetrain, controller::getRightY, controller::getLeftY);
    }
}