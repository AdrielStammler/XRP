package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

public class TurnDegrees extends Command {
    private final XRPDrivetrain drivetrain;
    private final double speed;
    private final double degrees;

    public TurnDegrees(XRPDrivetrain drivetrain, double speed, double degrees) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.degrees = degrees;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.arcadeDrive(0.0, 0.0);
        drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(0.0, speed);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        double inchPerDegree = Math.PI * 6.102 / 360;
        return getAverageTurningDistance() >= inchPerDegree * degrees;
    }

    private double getAverageTurningDistance() {
        double leftD = Math.abs(drivetrain.getLeftDistanceInch());
        double rightD = Math.abs(drivetrain.getRightDistanceInch());
        return (leftD + rightD) / 2.0;
    }
}
